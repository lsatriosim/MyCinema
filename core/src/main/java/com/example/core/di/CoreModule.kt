package com.example.core.di

import androidx.room.Room
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.remoteDataSource
import com.example.core.domain.repository.IMovieRepository
import com.example.core.domain.repository.MovieRepository
import com.example.core.domain.usecase.MovieInteractor
import com.example.core.domain.usecase.MovieUseCase
import com.example.core.utils.AppExecutors
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory{ get<MovieDatabase>().movieDao()}
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZmVmYmM0ZmE5OTBlZGRmYzFlNzUzZGI4ZTNiZTIzYSIsInN1YiI6IjY0ZTU2YWRkMDZmOTg0MDBhZTQ3ODQ1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9qj4Wtk5xKnO_BpBey7jvMTkjFZVdLSGEY6--GH7WzI")
                    .build()
                chain.proceed(requestHeaders)})
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single{
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module{
    single { com.example.core.data.source.local.LocalDataSource(get()) }
    single { remoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}

val useCaseModule = module{
    factory<MovieUseCase>{MovieInteractor(get())}
}
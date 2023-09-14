package com.example.core.domain.repository

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.remoteDataSource
import com.example.core.data.source.remote.response.ResultsItem
import com.example.core.domain.model.Movie
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository (
    private val localDataSource: com.example.core.data.source.local.LocalDataSource,
    private val remoteDataSource: remoteDataSource,
    private val appExecutor : AppExecutors
): IMovieRepository {
    override fun getAllMovies(): Flow<com.example.core.data.Resource<List<Movie>>> =
        object : com.example.core.data.NetworkBoundResource<List<Movie>, List<ResultsItem>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.movieEntitytoMovie(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.movieResponseToEntities(data)
                localDataSource.insertMovies(movieList)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
                DataMapper.movieEntitytoMovie(it)
            }
    }

    override fun setFavoriteMovies(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.movieToMovieEntity(movie)
        appExecutor.diskIO().execute{localDataSource.setFavoriteMovies(movieEntity, state)}
    }

    override fun getDetailMovie(movieId: Int): Flow<Movie> {
        return localDataSource.getDetailMovie(movieId).map {
            DataMapper.movieEntityToMovie(it)
        }
    }
}
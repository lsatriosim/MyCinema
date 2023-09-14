package com.example.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val movieDao: MovieDao){
    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insert(movieList)

    fun setFavoriteMovies(movie: MovieEntity, newState:Boolean){
        movie.isFavorite = newState
        movieDao.update(movie)
    }

    fun getDetailMovie(movieId: Int): Flow<MovieEntity> = movieDao.getDetailMovie(movieId)
}
package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<com.example.core.data.Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovies(movie:Movie, state:Boolean)

    fun getDetailMovie(movieId: Int): Flow<Movie>
}
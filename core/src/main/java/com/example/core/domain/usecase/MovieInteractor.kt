package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovies(): Flow<com.example.core.data.Resource<List<Movie>>> {
        return movieRepository.getAllMovies()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovies()
    }

    override fun setFavoriteMovies(movie: Movie, state: Boolean) {
        movieRepository.setFavoriteMovies(movie, state)
    }

    override fun getDetailMovie(movieId: Int): Flow<Movie> {
        return movieRepository.getDetailMovie(movieId)
    }
}
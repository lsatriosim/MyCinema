package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.ResultsItem
import com.example.core.domain.model.Movie

object DataMapper {
    fun movieResponseToEntities(input: List<ResultsItem>): List<MovieEntity>{
        val movieEntityList = ArrayList<MovieEntity>()
        input.map{
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieEntityList.add(movie)
        }
        return movieEntityList
    }

    fun movieEntitytoMovie(input: List<MovieEntity>): List<Movie> =
        input.map{
            Movie(
                id = it.id,
                title = it.title!!,
                overview = it.overview!!,
                posterPath = it.posterPath!!,
                backdropPath = it.backdropPath!!,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun movieToMovieEntity(input: Movie): MovieEntity =
            MovieEntity(
                id = input.id,
                title = input.title,
                overview = input.overview,
                posterPath = input.posterPath,
                backdropPath = input.backdropPath,
                popularity = input.popularity,
                voteAverage = input.voteAverage,
                voteCount = input.voteCount,
                isFavorite = input.isFavorite
            )

    fun movieEntityToMovie(input: MovieEntity): Movie =
        Movie(
            id = input.id,
            title = input.title!!,
            overview = input.overview!!,
            posterPath = input.posterPath!!,
            backdropPath = input.backdropPath!!,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )
}
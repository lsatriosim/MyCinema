package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieList: List<MovieEntity>)

    @Update
    fun update(movie: MovieEntity)

    @Query("SELECT * from movie ORDER BY id ASC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1 ORDER BY id ASC")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * From movie WHERE id = :movieId")
    fun getDetailMovie(movieId : Int): Flow<MovieEntity>

}
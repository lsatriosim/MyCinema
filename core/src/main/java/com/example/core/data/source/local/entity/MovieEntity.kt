package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = null,

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double = 0.0,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int = 0,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
package com.ian.app.muviepedia.core.data.cache.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class LocalMovieEntity(
    @PrimaryKey var localID: Int,
    @ColumnInfo(name = "imdb_movie_remote_id") var remoteId: Int,
    @ColumnInfo(name = "imdb_movie_tittle") var title: String,
    @ColumnInfo(name = "imdb_movie_poster_path") var poster_path: String,
    @ColumnInfo(name = "imdb_movie_timeStamp") val timeStamp: Long,
)
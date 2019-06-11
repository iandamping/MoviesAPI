package com.ian.app.muviepedia.data.local_data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 05/06/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_data")
data class LocalMovieData(@PrimaryKey(autoGenerate = true) var localID: Int?,
                          @ColumnInfo(name = "imdb_movie_id") var id: Int?,
                          @ColumnInfo(name = "imdb_movie_tittle") var title: String?,
                          @ColumnInfo(name = "imdb_movie_poster_path") var poster_path: String?)
package com.ian.app.muviepedia.core.data.cache.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_entity")
data class LocalTvEntity(
    @PrimaryKey var localID: Int,
    @ColumnInfo(name = "imdb_tv_remote_id") var remoteId: Int,
    @ColumnInfo(name = "imdb_tv_tittle") var title: String,
    @ColumnInfo(name = "imdb_tv_poster_path") var poster_path: String,
    @ColumnInfo(name = "imdb_tv_imdb_movie_timeStamp") val timeStamp: Long,
)

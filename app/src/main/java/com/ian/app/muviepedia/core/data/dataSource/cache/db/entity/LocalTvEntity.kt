package com.ian.app.muviepedia.core.data.dataSource.cache.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.util.MovieConstant

@Entity(tableName = "tv_entity")
data class LocalTvEntity(
    @PrimaryKey
    val localID: Int?,
    val remoteId: Int,
    val tvType: String,
    val posterPath: String,
    val originalName: String,
    val name: String,
    val popularity: Double,
    val voteCount: Int,
    val firstAirDate: String,
    val backdropPath: String,
    val originalLanguage: String,
    val voteAverage: Double,
    val overview: String,
    val timeStamp: Long,
)


fun TvDataResponse.mapToDatabase(type: String, timeStamp: Long): LocalTvEntity {
    return LocalTvEntity(
        localID = null,
        remoteId = this.id ?: 0,
        tvType = type,
        originalName = this.original_name ?: "No original name",
        posterPath = MovieConstant.imageFormatter + this.poster_path,
        voteCount = this.vote_count ?: 0,
        voteAverage = this.vote_average ?: 0.0,
        popularity = this.popularity ?: 0.0,
        originalLanguage = this.original_language ?: "No language",
        backdropPath = MovieConstant.imageFormatter + this.backdrop_path,
        overview = this.overview ?: "No overview",
        timeStamp = timeStamp,
        name = this.name ?: "No name",
        firstAirDate = this.first_air_date ?: "No data"
    )
}


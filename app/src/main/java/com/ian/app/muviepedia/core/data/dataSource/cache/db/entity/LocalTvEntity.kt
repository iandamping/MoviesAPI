package com.ian.app.muviepedia.core.data.dataSource.cache.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.imageFormatter
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse

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
        originalName = this.originalName ?: "No original name",
        posterPath = imageFormatter + this.posterPath,
        voteCount = this.voteCount ?: 0,
        voteAverage = this.voteAverage ?: 0.0,
        popularity = this.popularity ?: 0.0,
        originalLanguage = this.originalLanguage ?: "No language",
        backdropPath = imageFormatter + this.backdropPath,
        overview = this.overview ?: "No overview",
        timeStamp = timeStamp,
        name = this.name ?: "No name",
        firstAirDate = this.firstAirDate ?: "No data"
    )
}

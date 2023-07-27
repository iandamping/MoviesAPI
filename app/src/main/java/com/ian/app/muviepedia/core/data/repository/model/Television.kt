package com.ian.app.muviepedia.core.data.repository.model

import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalTvEntity
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse

data class Television(
    val originalName: String,
    val name: String,
    val popularity: Double,
    val voteCount: Int,
    val firstAirDate: String,
    val backdropPath: String,
    val originalLanguage: String,
    val id: Int,
    val voteAverage: Double,
    val overview: String,
    val posterPath: String
)


fun LocalTvEntity.mapToDomain(): Television = Television(
    originalName = this.originalName,
    name = this.name,
    popularity = this.popularity,
    voteCount = this.voteCount,
    firstAirDate = this.firstAirDate,
    backdropPath = this.backdropPath,
    originalLanguage = this.originalLanguage,
    id = this.remoteId,
    voteAverage = this.voteAverage,
    overview = this.overview,
    posterPath = this.posterPath
)

fun TvDataResponse.mapToDomain(): Television = Television(
    originalName = this.original_name ?: "",
    name = this.name ?: "",
    popularity = this.popularity ?: 0.0,
    voteCount = this.vote_count ?: 0,
    firstAirDate = this.first_air_date ?: "",
    backdropPath = this.backdrop_path ?: "",
    originalLanguage = this.original_language ?: "",
    id = this.id ?: 0,
    voteAverage = this.vote_average ?: 0.0,
    overview = this.overview ?: "",
    posterPath = this.poster_path ?: ""
)

fun List<LocalTvEntity>.mapLocalTelevisionListToDomain(): List<Television> = this.map { it.mapToDomain() }


fun List<TvDataResponse>.mapRemoteTelevisionListToDomain(): List<Television> = this.map { it.mapToDomain() }

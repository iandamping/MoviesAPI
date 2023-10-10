package com.ian.app.muviepedia.core.data.repository.model

import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalMovieEntity
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse

data class Movie(
    val voteCount: Int,
    val id: Int,
    val video: Boolean,
    val voteAverage: Double,
    val title: String,
    val popularity: Double,
    val posterPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String
)

fun LocalMovieEntity.mapToDomain(): Movie = Movie(
    voteCount = voteCount,
    id = remoteId,
    video = video,
    voteAverage = voteAverage,
    title = title,
    popularity = popularity,
    posterPath = posterPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    backdropPath = backdropPath,
    adult = adult,
    overview = overview,
    releaseDate = releaseDate
)

fun MovieDataResponse.mapToDomain(): Movie = Movie(
    voteCount = voteCount,
    id = id ?: 0,
    video = video ?: false,
    voteAverage = voteAverage ?: 0.0,
    title = title ?: "",
    popularity = popularity ?: 0.0,
    posterPath = posterPath ?: "",
    originalLanguage = originalLanguage ?: "",
    originalTitle = originalTitle ?: "",
    backdropPath = backdropPath ?: "",
    adult = adult ?: false,
    overview = overview ?: "",
    releaseDate = releaseDate ?: ""
)

fun List<LocalMovieEntity>.mapLocalMovieListToDomain(): List<Movie> = this.map { it.mapToDomain() }

fun List<MovieDataResponse>.mapRemoteMovieListToDomain(): List<Movie> = this.map { it.mapToDomain() }

package com.ian.app.muviepedia.core.data.repository.model

import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalMovieEntity
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse

data class Movie(
    val vote_count: Int,
    val id: Int,
    val video: Boolean,
    val vote_average: Double,
    val title: String,
    val popularity: Double,
    val poster_path: String,
    val original_language: String,
    val original_title: String,
    val backdrop_path: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String
)

fun LocalMovieEntity.mapToDomain(): Movie = Movie(
    vote_count = voteCount,
    id = remoteId,
    video = video,
    vote_average = voteAverage,
    title = title,
    popularity = popularity,
    poster_path = posterPath,
    original_language = originalLanguage,
    original_title = originalTitle,
    backdrop_path = backdropPath,
    adult = adult,
    overview = overview,
    release_date = release_date
)

fun MovieDataResponse.mapToDomain(): Movie = Movie(
    vote_count = vote_count,
    id = id ?: 0,
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    title = title ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    original_language = original_language ?: "",
    original_title = original_title ?: "",
    backdrop_path = backdrop_path ?: "",
    adult = adult ?: false,
    overview = overview ?: "",
    release_date = release_date ?: ""
)

fun List<LocalMovieEntity>.mapLocalMovieListToDomain(): List<Movie> = this.map { it.mapToDomain() }

fun List<MovieDataResponse>.mapRemoteMovieListToDomain(): List<Movie> = this.map { it.mapToDomain() }

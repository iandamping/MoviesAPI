package com.ian.app.muviepedia.core.data.dataSource.cache.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.util.MovieConstant.imageFormatter

@Entity(tableName = "movie_entity")
data class LocalMovieEntity(
    @PrimaryKey
    val localID: Int?,
    val remoteId: Int,
    val movieType: String,
    val title: String,
    val posterPath: String,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Double,
    val popularity: Double,
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val timeStamp: Long
)

fun MovieDataResponse.mapToDatabase(type: String, timeStamp: Long): LocalMovieEntity {
    return LocalMovieEntity(
        localID = null,
        remoteId = this.id ?: 0,
        movieType = type,
        title = this.title ?: "No tittle",
        posterPath = imageFormatter + this.poster_path,
        voteCount = this.vote_count,
        video = this.video ?: false,
        voteAverage = this.vote_average ?: 0.0,
        popularity = this.popularity ?: 0.0,
        originalLanguage = this.original_language ?: "No language",
        originalTitle = this.original_title ?: "No title",
        backdropPath = imageFormatter + this.backdrop_path,
        adult = this.video ?: false,
        overview = this.overview ?: "No overview",
        release_date = this.release_date ?: "No release date",
        timeStamp = timeStamp
    )
}

fun List<MovieDataResponse>.mapListToDomain(type: String, timeStamp: Long) =
    map { it.mapToDatabase(type, timeStamp) }
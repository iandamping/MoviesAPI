package com.ian.app.muviepedia.core.data.dataSource.cache.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.imageFormatter
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse

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
    val releaseDate: String,
    val timeStamp: Long
)

fun MovieDataResponse.mapToDatabase(type: String, timeStamp: Long): LocalMovieEntity {
    return LocalMovieEntity(
        localID = null,
        remoteId = this.id ?: 0,
        movieType = type,
        title = this.title ?: "No tittle",
        posterPath = imageFormatter + this.posterPath,
        voteCount = this.voteCount,
        video = this.video ?: false,
        voteAverage = this.voteAverage ?: 0.0,
        popularity = this.popularity ?: 0.0,
        originalLanguage = this.originalLanguage ?: "No language",
        originalTitle = this.originalTitle ?: "No title",
        backdropPath = imageFormatter + this.backdropPath,
        adult = this.video ?: false,
        overview = this.overview ?: "No overview",
        releaseDate = this.releaseDate ?: "No release date",
        timeStamp = timeStamp
    )
}

fun List<MovieDataResponse>.mapListToDomain(type: String, timeStamp: Long) =
    map { it.mapToDatabase(type, timeStamp) }

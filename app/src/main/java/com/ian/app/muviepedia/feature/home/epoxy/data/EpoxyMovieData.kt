package com.ian.app.muviepedia.feature.home.epoxy.data

sealed class EpoxyMovieData {

    data class Shimmer(val epoxyId: Int) : EpoxyMovieData()

    data class MovieData(
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
    ) : EpoxyMovieData()

    object Error : EpoxyMovieData()
}
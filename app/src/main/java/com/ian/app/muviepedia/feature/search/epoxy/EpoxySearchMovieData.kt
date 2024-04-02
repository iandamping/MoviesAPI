package com.ian.app.muviepedia.feature.search.epoxy

sealed class EpoxySearchMovieData {

    data class Shimmer(val epoxyId: Int) : EpoxySearchMovieData()

    data class MovieData(
        val epoxyId: Int,
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
    ) : EpoxySearchMovieData()

    object Error : EpoxySearchMovieData()
}

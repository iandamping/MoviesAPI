package com.ian.app.muviepedia.feature.home.epoxy.movie.topRated

sealed class EpoxyTopRatedMovieData {

    data class Shimmer(val epoxyId: Int) : EpoxyTopRatedMovieData()

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
    ) : EpoxyTopRatedMovieData()

    object Error : EpoxyTopRatedMovieData()
}

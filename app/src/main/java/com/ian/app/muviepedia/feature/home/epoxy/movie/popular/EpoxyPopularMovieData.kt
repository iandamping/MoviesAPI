package com.ian.app.muviepedia.feature.home.epoxy.movie.popular

sealed class EpoxyPopularMovieData {

    data class Shimmer(val epoxyId: Int) : EpoxyPopularMovieData()

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
    ) : EpoxyPopularMovieData()

    object Error : EpoxyPopularMovieData()
}

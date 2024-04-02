package com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying

sealed class EpoxyNowPlayingMovieData {

    data class Shimmer(val epoxyId: Int) : EpoxyNowPlayingMovieData()

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
    ) : EpoxyNowPlayingMovieData()

    object Error : EpoxyNowPlayingMovieData()
}

package com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying

sealed class EpoxyNowPlayingMovieData {

    data class Shimmer(val epoxyId: Int) : EpoxyNowPlayingMovieData()

    data class MovieData(
        val epoxyId: Int,
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
    ) : EpoxyNowPlayingMovieData()

    object Error : EpoxyNowPlayingMovieData()
}

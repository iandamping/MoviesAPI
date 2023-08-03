package com.ian.app.muviepedia.feature.home.epoxy.controller

import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData

data class EpoxyHomeData(
    val popularMovie: List<EpoxyPopularMovieData>,
    val nowPlayingMovie: List<EpoxyNowPlayingMovieData>,
    val topRatedMovie: List<EpoxyTopRatedMovieData>,
    val upComingMovie: List<EpoxyUpComingMovieData>,
    val popularTelevision: List<EpoxyPopularTelevisionData>,
    val topRatedTelevision: List<EpoxyTopRatedTelevisionData>,

    ) {
    companion object {
        fun init(): EpoxyHomeData {
            return EpoxyHomeData(
                popularMovie = emptyList(),
                nowPlayingMovie = emptyList(),
                topRatedMovie = emptyList(),
                upComingMovie = emptyList(),
                popularTelevision = emptyList(),
                topRatedTelevision = emptyList()
            )
        }
    }
}
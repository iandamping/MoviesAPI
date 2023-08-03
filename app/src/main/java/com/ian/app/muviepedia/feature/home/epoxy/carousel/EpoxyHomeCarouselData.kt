package com.ian.app.muviepedia.feature.home.epoxy.carousel

import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.television.EpoxyPopularTelevisionData

data class EpoxyHomeCarouselData(
    val popularMovie: List<EpoxyPopularMovieData>,
    val nowPlayingMovie: List<EpoxyNowPlayingMovieData>,
    val topRatedMovie: List<EpoxyTopRatedMovieData>,
    val upComingMovie: List<EpoxyUpComingMovieData>,
    val popularTelevision: List<EpoxyPopularTelevisionData>,

    ) {
    companion object {
        fun init(): EpoxyHomeCarouselData {
            return EpoxyHomeCarouselData(
                popularMovie = emptyList(),
                nowPlayingMovie = emptyList(),
                topRatedMovie = emptyList(),
                upComingMovie = emptyList(),
                popularTelevision = emptyList()
            )
        }
    }
}
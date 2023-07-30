package com.ian.app.muviepedia.feature.home.epoxy.carousel

import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxyUpComingMovieData

data class EpoxyHomeCarouselData(
    val popular: List<EpoxyPopularMovieData>,
    val nowPlaying: List<EpoxyNowPlayingMovieData>,
    val topRated: List<EpoxyTopRatedMovieData>,
    val upComing: List<EpoxyUpComingMovieData>,
) {
    companion object {
        fun init(): EpoxyHomeCarouselData {
            return EpoxyHomeCarouselData(
                popular = emptyList(),
                nowPlaying = emptyList(),
                topRated = emptyList(),
                upComing = emptyList()
            )
        }
    }
}
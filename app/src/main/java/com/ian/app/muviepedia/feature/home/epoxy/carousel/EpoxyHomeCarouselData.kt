package com.ian.app.muviepedia.feature.home.epoxy.carousel

import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData

data class EpoxyHomeCarouselData(
    val popular: List<EpoxyPopularMovieData>,
    val nowPlaying: List<EpoxyNowPlayingMovieData>,
) {
    companion object {
        fun init(): EpoxyHomeCarouselData {
            return EpoxyHomeCarouselData(
                popular = emptyList(),
                nowPlaying = emptyList()
            )
        }
    }
}
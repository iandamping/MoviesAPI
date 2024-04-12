package com.ian.app.muviepedia.feature.home.epoxy.controller

import com.ian.app.muviepedia.feature.home.epoxy.data.EpoxyMovieData
import com.ian.app.muviepedia.feature.home.epoxy.television.data.EpoxyTelevisionData

data class EpoxyHomeData(
    val popularMovie: List<EpoxyMovieData>,
    val nowPlayingMovie: List<EpoxyMovieData>,
    val topRatedMovie: List<EpoxyMovieData>,
    val upComingMovie: List<EpoxyMovieData>,
    val popularTelevision: List<EpoxyTelevisionData>,
    val topRatedTelevision: List<EpoxyTelevisionData>,

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

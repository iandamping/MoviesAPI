package com.ian.app.muviepedia.feature.home.epoxy.nowPlaying

import com.airbnb.epoxy.TypedEpoxyController

class EpoxyNowPlayingMovieController(private val clickListener: EpoxyNowPlayingMovieControllerListener) :
    TypedEpoxyController<List<EpoxyNowPlayingMovieData>>() {

    interface EpoxyNowPlayingMovieControllerListener {
        fun onNowPlayingClick(id: Int)
    }

    override fun buildModels(data: List<EpoxyNowPlayingMovieData>?) {
        data?.forEach { multiData ->
            when (multiData) {
                is EpoxyNowPlayingMovieData.Shimmer -> {
                    EpoxyShimmerNowPlayingMovie()
                        .id(multiData.epoxyId)
                        .addTo(this)
                }

                is EpoxyNowPlayingMovieData.MovieData -> {
                    EpoxySuccessNowPlayingMovie(
                        data = multiData,
                        clickListener = clickListener::onNowPlayingClick
                    )
                        .id(multiData.epoxyId)
                        .addTo(this)
                }

                EpoxyNowPlayingMovieData.Error -> {
                    EpoxyErrorNowPlayingMovie()
                        .id("error")
                        .addTo(this)
                }
            }
        }
    }
}
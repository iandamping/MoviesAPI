package com.ian.app.muviepedia.feature.home.epoxy.popular

import com.airbnb.epoxy.TypedEpoxyController

class EpoxyPopularMovieController(private val clickListener: EpoxyPopularMovieControllerListener) :
    TypedEpoxyController<List<EpoxyPopularMovieData>>() {

    interface EpoxyPopularMovieControllerListener {
        fun onPopularMovieClick(id: Int)
    }

    override fun buildModels(data: List<EpoxyPopularMovieData>?) {
        data?.forEach { multiData ->
            when (multiData) {
                is EpoxyPopularMovieData.Shimmer -> {
                    EpoxyShimmerPopularMovie()
                        .id(multiData.epoxyId)
                        .addTo(this)
                }

                is EpoxyPopularMovieData.MovieData -> {
                    EpoxySuccessPopularMovie(
                        data = multiData,
                        clickListener = clickListener::onPopularMovieClick
                    )
                        .id(multiData.epoxyId)
                        .addTo(this)
                }

                EpoxyPopularMovieData.Error -> {
                    EpoxyErrorPopularMovie()
                        .id("error")
                        .addTo(this)
                }
            }
        }
    }
}
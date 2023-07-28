package com.ian.app.muviepedia.feature.home.epoxy.carousel

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyErrorNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyShimmerNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxySuccessNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyErrorPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyShimmerPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxySuccessPopularMovie

class EpoxyHomeController(
    private val clickListener1: EpoxyNowPlayingMovieControllerListener,
    private val clickListener2: EpoxyPopularMovieControllerListener
) : TypedEpoxyController<EpoxyHomeCarouselData>() {

    interface EpoxyNowPlayingMovieControllerListener {
        fun onNowPlayingClick(id: Int)
    }


    interface EpoxyPopularMovieControllerListener {
        fun onPopularMovieClick(id: Int)
    }

    override fun buildModels(data: EpoxyHomeCarouselData?) {
        //carousel popular movie
        if (data != null) {
            if (data.popular.isNotEmpty()) {
                val carouselPopularModel = data.popular.map { multiData ->
                    when (multiData) {
                        is EpoxyPopularMovieData.Shimmer -> {
                            EpoxyShimmerPopularMovie()
                                .id(multiData.epoxyId)
                        }

                        is EpoxyPopularMovieData.MovieData -> {
                            EpoxySuccessPopularMovie(
                                data = multiData,
                                clickListener = clickListener2::onPopularMovieClick
                            )
                                .id(multiData.epoxyId)
                        }

                        EpoxyPopularMovieData.Error -> {
                            EpoxyErrorPopularMovie()
                                .id("error")
                        }
                    }
                }
                CarouselModel_()
                    .id("movie_popular")
                    .models(carouselPopularModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }
        //carouse now playing movie
        if (data!=null){
            if (data.nowPlaying.isNotEmpty()) {
                val carouselNowPlayingModel = data.nowPlaying.map { multiData ->
                    when (multiData) {
                        is EpoxyNowPlayingMovieData.Shimmer -> {
                            EpoxyShimmerNowPlayingMovie()
                                .id(multiData.epoxyId)
                        }

                        is EpoxyNowPlayingMovieData.MovieData -> {
                            EpoxySuccessNowPlayingMovie(
                                data = multiData,
                                clickListener = clickListener1::onNowPlayingClick
                            )
                                .id(multiData.epoxyId)
                        }

                        EpoxyNowPlayingMovieData.Error -> {
                            EpoxyErrorNowPlayingMovie()
                                .id("error")
                        }
                    }
                }

                CarouselModel_()
                    .id("movie_now_playing")
                    .models(carouselNowPlayingModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }

    }
}
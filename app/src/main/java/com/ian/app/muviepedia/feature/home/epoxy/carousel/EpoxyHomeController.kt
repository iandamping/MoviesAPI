package com.ian.app.muviepedia.feature.home.epoxy.carousel

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyErrorNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyShimmerNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxySuccessNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyErrorPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyShimmerPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxySuccessPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxyErrorTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxyShimmerTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxySuccessTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxyErrorUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxyShimmerUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxySuccessUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.television.EpoxyErrorPopularTelevision
import com.ian.app.muviepedia.feature.home.television.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.television.EpoxyShimmerPopularTelevision
import com.ian.app.muviepedia.feature.home.television.EpoxySuccessPopularTelevision
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyHomeController(
    private val viewHelper: ViewHelper,
    private val clickListener1: EpoxyNowPlayingMovieControllerListener,
    private val clickListener2: EpoxyPopularMovieControllerListener,
    private val clickListener3: EpoxyTopRatedMovieControllerListener,
    private val clickListener4: EpoxyUpComingMovieControllerListener,
    private val clickListener5: EpoxyPopularTelevisionControllerListener
) : TypedEpoxyController<EpoxyHomeCarouselData>() {

    interface EpoxyNowPlayingMovieControllerListener {
        fun onNowPlayingClick(id: Int)
    }

    interface EpoxyPopularMovieControllerListener {
        fun onPopularMovieClick(id: Int)
    }

    interface EpoxyTopRatedMovieControllerListener {
        fun onTopRatedMovieClick(id: Int)
    }

    interface EpoxyUpComingMovieControllerListener {
        fun onUpComingMovieClick(id: Int)
    }

    interface EpoxyPopularTelevisionControllerListener {
        fun onPopularTelevisionClick(id: Int)
    }

    override fun buildModels(data: EpoxyHomeCarouselData?) {
        EpoxyCommonTitle("Popular Movie", 18)
            .id("movie_popular_title")
            .addTo(this)
        //carousel popular movie
        if (data != null) {
            if (data.popularMovie.isNotEmpty()) {
                val carouselPopularModel = data.popularMovie.map { multiData ->
                    when (multiData) {
                        is EpoxyPopularMovieData.Shimmer -> {
                            EpoxyShimmerPopularMovie()
                                .id(multiData.epoxyId)
                        }

                        is EpoxyPopularMovieData.MovieData -> {
                            EpoxySuccessPopularMovie(
                                viewHelper = viewHelper,
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

        EpoxyCommonTitle("Now Playing Movie", 16)
            .id("movie_now_playing_title")
            .addTo(this)
        //carouse now playing movie
        if (data != null) {
            if (data.nowPlayingMovie.isNotEmpty()) {
                val carouselNowPlayingModel = data.nowPlayingMovie.map { multiData ->
                    when (multiData) {
                        is EpoxyNowPlayingMovieData.Shimmer -> {
                            EpoxyShimmerNowPlayingMovie()
                                .id(multiData.epoxyId)
                        }

                        is EpoxyNowPlayingMovieData.MovieData -> {
                            EpoxySuccessNowPlayingMovie(
                                viewHelper = viewHelper,
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

        EpoxyCommonTitle("Top Rated", 16)
            .id("movie_top_rated_title")
            .addTo(this)
        if (data != null) {
            if (data.nowPlayingMovie.isNotEmpty()) {
                val carouselTopRatedModel = data.topRatedMovie.map { multiData ->
                    when (multiData) {
                        is EpoxyTopRatedMovieData.Shimmer -> {
                            EpoxyShimmerTopRatedMovie()
                                .id(multiData.epoxyId)
                        }

                        is EpoxyTopRatedMovieData.MovieData -> {
                            EpoxySuccessTopRatedMovie(
                                viewHelper = viewHelper,
                                data = multiData,
                                clickListener = clickListener3::onTopRatedMovieClick
                            )
                                .id(multiData.epoxyId)
                        }

                        EpoxyTopRatedMovieData.Error -> {
                            EpoxyErrorTopRatedMovie()
                                .id("error")
                        }
                    }
                }

                CarouselModel_()
                    .id("movie_top_rated")
                    .models(carouselTopRatedModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }


        EpoxyCommonTitle("Up Coming", 16)
            .id("movie_up_coming_title")
            .addTo(this)
        if (data != null) {
            if (data.nowPlayingMovie.isNotEmpty()) {
                val carouselUpComingModel = data.upComingMovie.map { multiData ->
                    when (multiData) {
                        is EpoxyUpComingMovieData.Shimmer -> {
                            EpoxyShimmerUpComingMovie()
                                .id(multiData.epoxyId)
                        }

                        is EpoxyUpComingMovieData.MovieData -> {
                            EpoxySuccessUpComingMovie(
                                viewHelper = viewHelper,
                                data = multiData,
                                clickListener = clickListener4::onUpComingMovieClick
                            )
                                .id(multiData.epoxyId)
                        }

                        EpoxyUpComingMovieData.Error -> {
                            EpoxyErrorUpComingMovie()
                                .id("error")
                        }
                    }
                }

                CarouselModel_()
                    .id("movie_up_coming")
                    .models(carouselUpComingModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }


        EpoxyCommonTitle("Popular Television", 18)
            .id("television_popular_title")
            .addTo(this)
        //carousel popular movie
        if (data != null) {
            if (data.popularTelevision.isNotEmpty()) {
                val carouselPopularModel = data.popularTelevision.map { multiData ->
                    when (multiData) {
                        is EpoxyPopularTelevisionData.Shimmer -> {
                            EpoxyShimmerPopularTelevision()
                                .id(multiData.epoxyId)
                        }

                        is EpoxyPopularTelevisionData.TelevisionData -> {
                            EpoxySuccessPopularTelevision(
                                viewHelper = viewHelper,
                                data = multiData,
                                clickListener = clickListener5::onPopularTelevisionClick
                            )
                                .id(multiData.epoxyId)
                        }

                        EpoxyPopularTelevisionData.Error -> {
                            EpoxyErrorPopularTelevision()
                                .id("error")
                        }
                    }
                }
                CarouselModel_()
                    .id("television_popular")
                    .models(carouselPopularModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }

    }
}
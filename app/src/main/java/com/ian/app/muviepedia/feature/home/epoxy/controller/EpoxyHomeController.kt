package com.ian.app.muviepedia.feature.home.epoxy.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyErrorNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyShimmerNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxySuccessNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyErrorPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyShimmerPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxySuccessPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyErrorTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyShimmerTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxySuccessTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyErrorUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyShimmerUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxySuccessUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyErrorPopularTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyShimmerPopularTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxySuccessPopularTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyErrorTopRatedTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyShimmerTopRatedTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxySuccessTopRatedTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData
import com.ian.app.muviepedia.util.epoxy.HorizontalGridCarouselModel_
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyHomeController(
    private val viewHelper: ViewHelper,
    private val movieClickListener: EpoxyMovieControllerListener,
    private val televisionClickListener: EpoxyTelevisionControllerListener,
) : TypedEpoxyController<EpoxyHomeData>() {


    interface EpoxyMovieControllerListener {
        fun onMovieClick(id: Int)
    }

    interface EpoxyTelevisionControllerListener {
        fun onTelevisionClick(id: Int)
    }


    override fun buildModels(data: EpoxyHomeData?) {
        EpoxyCommonTitle("Popular Movie", 18)
            .id("1_movie_popular_title")
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
                                clickListener = movieClickListener::onMovieClick
                            )
                                .id(multiData.epoxyId)
                        }

                        EpoxyPopularMovieData.Error -> {
                            EpoxyErrorPopularMovie()
                                .id("error")
                        }
                    }
                }
                HorizontalGridCarouselModel_()
                    .id("1_movie_popular")
                    .models(carouselPopularModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }

        EpoxyCommonTitle("Now Playing Movie", 16)
            .id("2_movie_now_playing_title")
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
                                clickListener = movieClickListener::onMovieClick
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
                    .id("2_movie_now_playing")
                    .models(carouselNowPlayingModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }

        EpoxyCommonTitle("Top Rated", 16)
            .id("3_movie_top_rated_title")
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
                                clickListener = movieClickListener::onMovieClick
                            )
                                .id(multiData.epoxyId)
                        }

                        EpoxyTopRatedMovieData.Error -> {
                            EpoxyErrorTopRatedMovie()
                                .id("error")
                        }
                    }
                }

                HorizontalGridCarouselModel_()
                    .id("3_movie_top_rated")
                    .models(carouselTopRatedModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }


        EpoxyCommonTitle("Up Coming", 16)
            .id("4_movie_up_coming_title")
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
                                clickListener = movieClickListener::onMovieClick
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
                    .id("4_movie_up_coming")
                    .models(carouselUpComingModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }


        EpoxyCommonTitle("Popular Television", 18)
            .id("5_television_popular_title")
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
                                clickListener = televisionClickListener::onTelevisionClick
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
                    .id("5_television_popular")
                    .models(carouselPopularModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }



        EpoxyCommonTitle("Top Rated Television", 18)
            .id("6_television_toprated_title")
            .addTo(this)
        //carousel popular movie
        if (data != null) {
            if (data.topRatedTelevision.isNotEmpty()) {
                val carouselTopRatedModel = data.topRatedTelevision.map { multiData ->
                    when (multiData) {
                        is EpoxyTopRatedTelevisionData.Shimmer -> {
                            EpoxyShimmerTopRatedTelevision()
                                .id(multiData.epoxyId)
                        }

                        is EpoxyTopRatedTelevisionData.TelevisionData -> {
                            EpoxySuccessTopRatedTelevision(
                                viewHelper = viewHelper,
                                data = multiData,
                                clickListener = televisionClickListener::onTelevisionClick
                            )
                                .id(multiData.epoxyId)
                        }

                        EpoxyTopRatedTelevisionData.Error -> {
                            EpoxyErrorTopRatedTelevision()
                                .id("error")
                        }
                    }
                }
                HorizontalGridCarouselModel_()
                    .id("6_television_toprated")
                    .models(carouselTopRatedModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }
        }

    }
}
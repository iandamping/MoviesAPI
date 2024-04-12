package com.ian.app.muviepedia.feature.home.epoxy.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.home.epoxy.data.EpoxyMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyErrorNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyShimmerNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxySuccessNowPlayingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyErrorPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyShimmerPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxySuccessPopularMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.search.EpoxySearchMovieHome
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyErrorTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyShimmerTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxySuccessTopRatedMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyErrorUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyShimmerUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxySuccessUpComingMovie
import com.ian.app.muviepedia.feature.home.epoxy.television.data.EpoxyTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyErrorPopularTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyShimmerPopularTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxySuccessPopularTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.search.EpoxySearchTelevisionHome
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyErrorTopRatedTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyShimmerTopRatedTelevision
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxySuccessTopRatedTelevision
import com.ian.app.muviepedia.util.epoxy.HorizontalGridCarouselModel_
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyHomeController(
    private val viewHelper: ViewHelper,
    private val movieClickListener: EpoxyMovieControllerListener,
    private val televisionClickListener: EpoxyTelevisionControllerListener,
    private val epoxySearchHomeControllerListener: EpoxySearchHomeControllerListener,
    private val epoxySearchTelevisionControllerListener: EpoxySearchTelevisionControllerListener,
) : TypedEpoxyController<EpoxyHomeData>() {

    interface EpoxyMovieControllerListener {
        fun onMovieClick(id: Int)
    }

    interface EpoxyTelevisionControllerListener {
        fun onTelevisionClick(id: Int)
    }

    interface EpoxySearchHomeControllerListener {
        fun onSearchMovieCLicked()
    }

    interface EpoxySearchTelevisionControllerListener {
        fun onSearchTelevisionCLicked()
    }

    override fun buildModels(data: EpoxyHomeData?) {
        if (data != null) {
            EpoxySearchMovieHome(onSearchButtonClick = epoxySearchHomeControllerListener::onSearchMovieCLicked)
                .id("0_movie_search")
                .addTo(this)

            EpoxyCommonTitle(title = "Popular Movie", fontSize = 18, viewHelper = viewHelper)
                .id("1_movie_popular_title")
                .addTo(this)
            // carousel popular movie
            implementEpoxyPopularMovie(data)

            EpoxyCommonTitle(title = "Top Rated Movie", fontSize = 16, viewHelper = viewHelper)
                .id("3_movie_top_rated_title")
                .addTo(this)
            // carousel top rated movie
            implementEpoxyTopRatedMovie(data)

            EpoxyCommonTitle(title = "Up Coming Movie", fontSize = 16, viewHelper = viewHelper)
                .id("4_movie_up_coming_title")
                .addTo(this)
            // carousel up coming movie
            implementEpoxyUpComingMovie(data)

            EpoxyCommonTitle(title = "Now Playing Movie", fontSize = 16, viewHelper = viewHelper)
                .id("5_movie_now_playing_title")
                .addTo(this)
            // carousel up coming movie
            implementEpoxyNowPlayingMovie(data)

            EpoxySearchTelevisionHome(onSearchButtonClick = epoxySearchTelevisionControllerListener::onSearchTelevisionCLicked)
                .id("6_television_search")
                .addTo(this)

            EpoxyCommonTitle(title = "Popular Television", fontSize = 18, viewHelper = viewHelper)
                .id("7_television_popular_title")
                .addTo(this)
            // carousel popular television
            implementEpoxyPopularTelevision(data)

            EpoxyCommonTitle(title = "Top Rated Television", fontSize = 18, viewHelper = viewHelper)
                .id("8_television_toprated_title")
                .addTo(this)
            // carousel top rated television
            implementEpoxyTopRatedTelevision(data)
        }
    }

    private fun implementEpoxyTopRatedTelevision(data: EpoxyHomeData) {
        if (data.topRatedTelevision.isNotEmpty()) {
            val carouselTopRatedModel = data.topRatedTelevision.map { multiData ->
                when (multiData) {
                    is EpoxyTelevisionData.Shimmer ->
                        EpoxyShimmerTopRatedTelevision()
                            .id(multiData.epoxyId)

                    is EpoxyTelevisionData.TelevisionData ->
                        EpoxySuccessTopRatedTelevision(
                            viewHelper = viewHelper,
                            data = multiData,
                            clickListener = televisionClickListener::onTelevisionClick
                        )
                            .id(multiData.id)

                    EpoxyTelevisionData.Error ->
                        EpoxyErrorTopRatedTelevision()
                            .id("error")
                }
            }
            HorizontalGridCarouselModel_()
                .id("6_television_toprated")
                .models(carouselTopRatedModel)
                .numViewsToShowOnScreen(2.5f)
                .addTo(this)
        } else {
            EpoxyErrorTopRatedTelevision()
                .id("error")
                .addTo(this)
        }
    }

    private fun implementEpoxyPopularTelevision(data: EpoxyHomeData) {
        if (data.popularTelevision.isNotEmpty()) {
            val carouselPopularModel = data.popularTelevision.map { multiData ->
                when (multiData) {
                    is EpoxyTelevisionData.Shimmer ->
                        EpoxyShimmerPopularTelevision()
                            .id(multiData.epoxyId)

                    is EpoxyTelevisionData.TelevisionData ->
                        EpoxySuccessPopularTelevision(
                            viewHelper = viewHelper,
                            data = multiData,
                            clickListener = televisionClickListener::onTelevisionClick
                        )
                            .id(multiData.id)

                    EpoxyTelevisionData.Error ->
                        EpoxyErrorPopularTelevision()
                            .id("error")
                }
            }
            CarouselModel_()
                .id("5_television_popular")
                .models(carouselPopularModel)
                .numViewsToShowOnScreen(1.5f)
                .addTo(this)
        } else {
            EpoxyErrorPopularTelevision()
                .id("error")
                .addTo(this)
        }
    }

    private fun implementEpoxyUpComingMovie(data: EpoxyHomeData) {
        if (data.upComingMovie.isNotEmpty()) {
            val carouselUpComingModel = data.upComingMovie.map { multiData ->
                when (multiData) {
                    is EpoxyMovieData.Shimmer ->
                        EpoxyShimmerUpComingMovie()
                            .id(multiData.epoxyId)

                    is EpoxyMovieData.MovieData ->
                        EpoxySuccessUpComingMovie(
                            viewHelper = viewHelper,
                            data = multiData,
                            clickListener = movieClickListener::onMovieClick
                        )
                            .id(multiData.id)

                    EpoxyMovieData.Error ->
                        EpoxyErrorUpComingMovie()
                            .id("error")
                }
            }

            CarouselModel_()
                .id("4_movie_up_coming")
                .models(carouselUpComingModel)
                .numViewsToShowOnScreen(1.5f)
                .addTo(this)
        } else {
            EpoxyErrorUpComingMovie()
                .id("error")
                .addTo(this)
        }
    }

    private fun implementEpoxyTopRatedMovie(data: EpoxyHomeData) {
        if (data.topRatedMovie.isNotEmpty()) {
            val carouselTopRatedModel = data.topRatedMovie.map { multiData ->
                when (multiData) {
                    is EpoxyMovieData.Shimmer ->
                        EpoxyShimmerTopRatedMovie()
                            .id(multiData.epoxyId)

                    is EpoxyMovieData.MovieData ->
                        EpoxySuccessTopRatedMovie(
                            viewHelper = viewHelper,
                            data = multiData,
                            clickListener = movieClickListener::onMovieClick
                        )
                            .id(multiData.id)

                    EpoxyMovieData.Error ->
                        EpoxyErrorTopRatedMovie()
                            .id("error")
                }
            }

            HorizontalGridCarouselModel_()
                .id("3_movie_top_rated")
                .models(carouselTopRatedModel)
                .numViewsToShowOnScreen(2.5f)
                .addTo(this)
        } else {
            EpoxyErrorTopRatedMovie()
                .id("error")
                .addTo(this)
        }
    }

    private fun implementEpoxyNowPlayingMovie(data: EpoxyHomeData) {
        if (data.nowPlayingMovie.isNotEmpty()) {
            val carouselNowPlayingModel = data.nowPlayingMovie.map { multiData ->
                when (multiData) {
                    is EpoxyMovieData.Shimmer ->
                        EpoxyShimmerNowPlayingMovie()
                            .id(multiData.epoxyId)

                    is EpoxyMovieData.MovieData ->
                        EpoxySuccessNowPlayingMovie(
                            viewHelper = viewHelper,
                            data = multiData,
                            clickListener = movieClickListener::onMovieClick
                        )
                            .id(multiData.id)

                    EpoxyMovieData.Error ->
                        EpoxyErrorNowPlayingMovie()
                            .id("error")
                }
            }

            HorizontalGridCarouselModel_()
                .id("2_movie_now_playing")
                .models(carouselNowPlayingModel)
                .numViewsToShowOnScreen(2f)
                .addTo(this)
        } else {
            EpoxyErrorNowPlayingMovie()
                .id("error")
                .addTo(this)
        }
    }

    private fun implementEpoxyPopularMovie(data: EpoxyHomeData) {
        if (data.popularMovie.isNotEmpty()) {
            val carouselPopularModel = data.popularMovie.map { multiData ->
                when (multiData) {
                    is EpoxyMovieData.Shimmer ->
                        EpoxyShimmerPopularMovie()
                            .id(multiData.epoxyId)

                    is EpoxyMovieData.MovieData ->
                        EpoxySuccessPopularMovie(
                            viewHelper = viewHelper,
                            data = multiData,
                            clickListener = movieClickListener::onMovieClick
                        )
                            .id(multiData.id)

                    EpoxyMovieData.Error ->
                        EpoxyErrorPopularMovie()
                            .id("error")
                }
            }
            HorizontalGridCarouselModel_()
                .id("1_movie_popular")
                .models(carouselPopularModel)
                .numViewsToShowOnScreen(2f)
                .addTo(this)
        } else {
            EpoxyErrorPopularMovie()
                .id("error")
                .addTo(this)
        }
    }
}

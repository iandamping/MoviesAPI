package com.ian.app.muviepedia.feature.search.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.search.common.EpoxyInitSearchScreen
import com.ian.app.muviepedia.feature.search.common.EpoxyNoAvailableSearchScreen
import com.ian.app.muviepedia.feature.search.epoxy.EpoxyErrorSearchMovie
import com.ian.app.muviepedia.feature.search.epoxy.EpoxyShimmerSearchMovie
import com.ian.app.muviepedia.feature.search.epoxy.EpoxySuccessSearchMovie
import com.ian.app.muviepedia.feature.search.television.EpoxyErrorSearchTelevision
import com.ian.app.muviepedia.feature.search.television.EpoxyShimmerSearchTelevision
import com.ian.app.muviepedia.feature.search.television.EpoxySuccessSearchTelevision
import com.ian.app.muviepedia.feature.state.PresentationInputState
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySearchController(
    private val viewHelper: ViewHelper,
    private val movieClickListener: EpoxySearchMovieControllerListener,
) : TypedEpoxyController<EpoxySearchData>() {

    interface EpoxySearchMovieControllerListener {
        fun onMovieClick(id: Int)
    }

    //todo : this is next
    override fun buildModels(data: EpoxySearchData?) {
        // carousel search movie
        if (data != null) {
            when (data.uiState) {
                PresentationInputState.Loading -> {
                    when (data.flag) {
                        DetailFlag.Movie -> loadingSearchMovie()
                        DetailFlag.Television -> loadingSearchTelevision()
                    }
                }
                PresentationInputState.Success -> {
                    when (data.flag) {
                        DetailFlag.Movie -> successSearchMovie(data.movieData)
                        DetailFlag.Television -> successSearchTv(data.tvData)
                    }
                }

                PresentationInputState.Failed -> {
                    when (data.flag) {
                        DetailFlag.Movie -> errorSearchMovie()
                        DetailFlag.Television -> errorSearchTelevision()
                    }
                }

                PresentationInputState.Init -> initSearchContent(data.flag)
            }
        }
    }

    private fun initSearchContent(flag: DetailFlag) {
        EpoxyInitSearchScreen(flag.name)
            .id("1_search_movie")
            .addTo(this)
    }


    private fun loadingSearchMovie() {
        for (i in 0..20) {
            EpoxyShimmerSearchMovie()
                .id(i)
                .addTo(this)
        }
    }
    private fun loadingSearchTelevision() {
        for (i in 0..20) {
            EpoxyShimmerSearchTelevision()
                .id(i)
                .addTo(this)
        }
    }

    private fun successSearchMovie(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            movies.map { data ->
                EpoxySuccessSearchMovie(
                    viewHelper = viewHelper,
                    data = data,
                    clickListener = movieClickListener::onMovieClick
                ).id(data.id)
                    .addTo(this)
            }
        } else {
            noAvailableSearchData()
        }
    }

    private fun successSearchTv(movies: List<Television>) {
        if (movies.isNotEmpty()) {
            movies.map { data ->
                EpoxySuccessSearchTelevision(
                    viewHelper = viewHelper,
                    data = data,
                    clickListener = movieClickListener::onMovieClick
                ).id(data.id)
                    .addTo(this)
            }
        } else {
            noAvailableSearchData()
        }
    }

    private fun noAvailableSearchData() {
        EpoxyNoAvailableSearchScreen()
            .id("1_search")
            .addTo(this)
    }

    private fun errorSearchMovie() {
        EpoxyErrorSearchMovie()
            .id("1_search")
            .addTo(this)
    }

    private fun errorSearchTelevision() {
        EpoxyErrorSearchTelevision()
            .id("1_search")
            .addTo(this)
    }
}

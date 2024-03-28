package com.ian.app.muviepedia.feature.search.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.feature.search.epoxy.EpoxyErrorSearchMovie
import com.ian.app.muviepedia.feature.search.epoxy.EpoxyShimmerSearchMovie
import com.ian.app.muviepedia.feature.search.epoxy.EpoxySuccessSearchMovie
import com.ian.app.muviepedia.feature.state.PresentationInputState
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySearchController(
    private val viewHelper: ViewHelper,
    private val movieClickListener: EpoxySearchMovieControllerListener,
) : TypedEpoxyController<EpoxySearchData>() {

    interface EpoxySearchMovieControllerListener {
        fun onMovieClick(id: Int)
    }

    override fun buildModels(data: EpoxySearchData?) {
        // carousel search movie
        if (data != null) {
            when (data.uiState) {
                PresentationInputState.Loading -> loadingSearchMovie()
                PresentationInputState.Success -> successSearchMovie(data.searchMovie)
                PresentationInputState.Failed -> errorSearchMovie()
                PresentationInputState.Init -> initSearchMovie()
            }
        }
    }

    private fun initSearchMovie() {
//        EpoxyCommonTitle(title = "Search Movie", fontSize = 16, viewHelper = viewHelper)
//            .id("1_search_movie")
//            .addTo(this)
    }

    private fun loadingSearchMovie() {
        for (i in 0..20) {
            EpoxyShimmerSearchMovie()
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
            errorSearchMovie()
        }

    }

    private fun errorSearchMovie() {
        EpoxyErrorSearchMovie()
            .id("1_search_movie")
            .addTo(this)
    }
}

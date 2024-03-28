package com.ian.app.muviepedia.feature.search.controller

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.feature.state.PresentationInputState

data class EpoxySearchData(
    val uiState: PresentationInputState,
    val searchMovie: List<Movie>,
    val error: String
) {
    companion object {
        fun init(): EpoxySearchData {
            return EpoxySearchData(
                uiState = PresentationInputState.Init,
                searchMovie = emptyList(),
                error = "",
            )
        }
    }
}

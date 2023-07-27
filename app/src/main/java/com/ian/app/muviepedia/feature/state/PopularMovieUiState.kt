package com.ian.app.muviepedia.feature.state

import com.ian.app.muviepedia.core.data.repository.model.Movie

data class PopularMovieUiState(
    val uiState: PresentationState,
    val data: List<Movie>,
    val errorMessage: String
) {
    companion object {
        fun initialize(): PopularMovieUiState {
            return PopularMovieUiState(
                uiState = PresentationState.Loading,
                data = emptyList(),
                errorMessage = ""
            )
        }
    }

}

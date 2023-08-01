package com.ian.app.muviepedia.feature.state

import com.ian.app.muviepedia.core.data.repository.model.MovieDetail

data class DetailMovieUiState(
    val uiState: PresentationState,
    val data: MovieDetail?,
    val errorMessage: String
) {
    companion object {
        fun initialize(): DetailMovieUiState {
            return DetailMovieUiState(
                uiState = PresentationState.Loading,
                data = null,
                errorMessage = ""
            )
        }
    }

}

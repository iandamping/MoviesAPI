package com.ian.app.muviepedia.feature.state

import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevision
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag

data class DetailMovieUiState(
    val uiState: PresentationState,
    val movieData: MovieDetail?,
    val televisionData: TelevisionDetail?,
    val similarMovieData: Set<EpoxyMovie>,
    val similarTelevisionData: Set<EpoxyTelevision>,
    val flag: DetailFlag,
    val errorMessage: String
) {
    companion object {
        fun initialize(): DetailMovieUiState {
            return DetailMovieUiState(
                uiState = PresentationState.Loading,
                movieData = null,
                similarMovieData = emptySet(),
                televisionData = null,
                similarTelevisionData = emptySet(),
                flag = DetailFlag.MOVIE,
                errorMessage = ""
            )
        }
    }

}

package com.ian.app.muviepedia.feature.state

import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailContent
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag

data class DetailSimilarDataUIState(
    val uiState: PresentationState,
    val similarMovieData: Set<EpoxyMovie>,
    val similarTelevisionData: Set<EpoxyTelevisionDetailContent>,
    val flag: DetailFlag,
    val errorMessage: String
) {
    companion object {
        fun initialize(): DetailSimilarDataUIState {
            return DetailSimilarDataUIState(
                uiState = PresentationState.Loading,
                similarMovieData = emptySet(),
                similarTelevisionData = emptySet(),
                flag = DetailFlag.MOVIE,
                errorMessage = ""
            )
        }
    }
}


package com.ian.app.muviepedia.feature.state

import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag

data class DetailCompanyDataUiState(
    val uiState: PresentationState,
    val movieCompany: List<MovieDetail.ProductionCompany>,
    val televisionCompany: List<TelevisionDetail.ProductionCompany>,
    val flag: DetailFlag,
    val errorMessage: String
) {
    companion object {
        fun initialize(): DetailCompanyDataUiState {
            return DetailCompanyDataUiState(
                uiState = PresentationState.Loading,
                movieCompany = emptyList(),
                televisionCompany = emptyList(),
                flag = DetailFlag.Movie,
                errorMessage = ""
            )
        }
    }
}
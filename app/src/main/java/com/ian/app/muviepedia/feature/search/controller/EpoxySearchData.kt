package com.ian.app.muviepedia.feature.search.controller

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.state.PresentationInputState

data class EpoxySearchData(
    val uiState: PresentationInputState,
    val flag: DetailFlag,
    val movieData: List<Movie>,
    val tvData: List<Television>,
    val error: String
) {
    companion object {
        fun init(): EpoxySearchData {
            return EpoxySearchData(
                flag = DetailFlag.MOVIE,
                uiState = PresentationInputState.Init,
                movieData = emptyList(),
                tvData = emptyList(),
                error = "",
            )
        }
    }
}

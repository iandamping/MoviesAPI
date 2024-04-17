package com.ian.app.muviepedia.feature.state

import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailImageData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData

data class DetailDataUiState(
    val contentData: EpoxyDetailContentData?,
    val imageData: EpoxyDetailImageData?,
    val similarData: List<EpoxyDetailSimilarData>,
    val companyData: List<EpoxyDetailCompanyData>,
    val flag: DetailFlag,
) {
    companion object {
        fun initialize(): DetailDataUiState {
            return DetailDataUiState(
                similarData = emptyList(),
                companyData = emptyList(),
                flag = DetailFlag.Movie,
                contentData = null,
                imageData = null,
            )
        }
    }
}

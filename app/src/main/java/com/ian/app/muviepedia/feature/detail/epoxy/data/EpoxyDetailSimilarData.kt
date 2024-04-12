package com.ian.app.muviepedia.feature.detail.epoxy.data

sealed class EpoxyDetailSimilarData {

    data class Shimmer(val epoxyId: Int) : EpoxyDetailSimilarData()

    data class SimilarData(val epoxyImageId: Int, val image: String, val dataId: Int) :
        EpoxyDetailSimilarData()

    object Error : EpoxyDetailSimilarData()
}
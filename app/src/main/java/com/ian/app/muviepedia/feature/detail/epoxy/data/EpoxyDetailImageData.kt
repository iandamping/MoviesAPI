package com.ian.app.muviepedia.feature.detail.epoxy.data

sealed class EpoxyDetailImageData {

    data class Shimmer(val epoxyId: String) : EpoxyDetailImageData()

    data class ImageData(val epoxyImageId: String, val image: String) : EpoxyDetailImageData()

    object Error : EpoxyDetailImageData()
}
package com.ian.app.muviepedia.feature.detail.epoxy.data

sealed class EpoxyDetailVideoData {

    data class Shimmer(val epoxyId: Int) : EpoxyDetailVideoData()

    data class VideoData(
        val id: String,
        val key: String,
    ) : EpoxyDetailVideoData()

    object Error : EpoxyDetailVideoData()

}
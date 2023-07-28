package com.ian.app.muviepedia.feature.home.epoxy.nowPlaying

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerItemNowPlayingBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerNowPlayingMovie :
    ViewBindingEpoxyModelWithHolder<ShimmerItemNowPlayingBinding>() {
    override fun ShimmerItemNowPlayingBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerItemNowPlayingBinding.unbind() {
        shimmerProduct.stopShimmer()
    }


    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_item_now_playing
    }
}
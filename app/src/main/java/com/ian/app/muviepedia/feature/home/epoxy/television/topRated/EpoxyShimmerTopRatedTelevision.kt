package com.ian.app.muviepedia.feature.home.epoxy.television.topRated

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerItemTopRatedTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerTopRatedTelevision : ViewBindingEpoxyModelWithHolder<ShimmerItemTopRatedTelevisionBinding>() {

    override fun ShimmerItemTopRatedTelevisionBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerItemTopRatedTelevisionBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_item_top_rated_television
    }
}

package com.ian.app.muviepedia.feature.home.television

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerItemPopularTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerPopularTelevision :
    ViewBindingEpoxyModelWithHolder<ShimmerItemPopularTelevisionBinding>() {

    override fun ShimmerItemPopularTelevisionBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerItemPopularTelevisionBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_item_popular_television
    }
}
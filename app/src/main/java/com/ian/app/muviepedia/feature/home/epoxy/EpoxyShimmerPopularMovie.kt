package com.ian.app.muviepedia.feature.home.epoxy

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerItemPopularBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerPopularMovie : ViewBindingEpoxyModelWithHolder<ShimmerItemPopularBinding>() {
    override fun ShimmerItemPopularBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerItemPopularBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_item_popular
    }
}
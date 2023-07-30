package com.ian.app.muviepedia.feature.home.epoxy.topRated

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerTopRatedBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerTopRatedMovie : ViewBindingEpoxyModelWithHolder<ShimmerTopRatedBinding>() {
    override fun ShimmerTopRatedBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerTopRatedBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_top_rated
    }
}
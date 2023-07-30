package com.ian.app.muviepedia.feature.home.epoxy.upComing

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerUpComingBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerUpComingMovie : ViewBindingEpoxyModelWithHolder<ShimmerUpComingBinding>() {
    override fun ShimmerUpComingBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerUpComingBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_up_coming
    }
}
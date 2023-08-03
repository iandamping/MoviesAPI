package com.ian.app.muviepedia.feature.home.epoxy.upComing

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerUpComingMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerUpComingMovie : ViewBindingEpoxyModelWithHolder<ShimmerUpComingMovieBinding>() {
    override fun ShimmerUpComingMovieBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerUpComingMovieBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_up_coming_movie
    }
}
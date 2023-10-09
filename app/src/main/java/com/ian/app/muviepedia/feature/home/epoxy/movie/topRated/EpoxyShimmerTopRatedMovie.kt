package com.ian.app.muviepedia.feature.home.epoxy.movie.topRated

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerTopRatedMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerTopRatedMovie : ViewBindingEpoxyModelWithHolder<ShimmerTopRatedMovieBinding>() {
    override fun ShimmerTopRatedMovieBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerTopRatedMovieBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_top_rated_movie
    }
}

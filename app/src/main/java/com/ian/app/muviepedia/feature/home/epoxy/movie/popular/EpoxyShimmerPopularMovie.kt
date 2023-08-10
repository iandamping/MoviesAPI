package com.ian.app.muviepedia.feature.home.epoxy.movie.popular

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerItemPopularMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerPopularMovie : ViewBindingEpoxyModelWithHolder<ShimmerItemPopularMovieBinding>() {
    override fun ShimmerItemPopularMovieBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerItemPopularMovieBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_item_popular_movie
    }
}
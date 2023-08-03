package com.ian.app.muviepedia.feature.detail.epoxy.movie

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerDetailSimilarMovieContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerDetailSimilarMovieContent :
    ViewBindingEpoxyModelWithHolder<ShimmerDetailSimilarMovieContentBinding>() {
    override fun ShimmerDetailSimilarMovieContentBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerDetailSimilarMovieContentBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_detail_similar_movie_content
    }
}
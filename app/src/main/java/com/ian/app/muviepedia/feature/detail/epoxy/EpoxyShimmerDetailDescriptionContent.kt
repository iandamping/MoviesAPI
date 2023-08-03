package com.ian.app.muviepedia.feature.detail.epoxy

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerDetailMovieDescriptionContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerDetailDescriptionContent :
    ViewBindingEpoxyModelWithHolder<ShimmerDetailMovieDescriptionContentBinding>() {
    override fun ShimmerDetailMovieDescriptionContentBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerDetailMovieDescriptionContentBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_detail_movie_description_content
    }
}
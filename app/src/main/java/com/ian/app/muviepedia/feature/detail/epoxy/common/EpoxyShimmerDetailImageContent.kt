package com.ian.app.muviepedia.feature.detail.epoxy.common

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerMovieImageContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerDetailImageContent :
    ViewBindingEpoxyModelWithHolder<ShimmerMovieImageContentBinding>() {
    override fun ShimmerMovieImageContentBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerMovieImageContentBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_movie_image_content
    }
}
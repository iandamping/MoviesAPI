package com.ian.app.muviepedia.feature.detail.epoxy.common

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerDetailSimilarContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerDetailSimilarContent :
    ViewBindingEpoxyModelWithHolder<ShimmerDetailSimilarContentBinding>() {
    override fun ShimmerDetailSimilarContentBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerDetailSimilarContentBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_detail_similar_content
    }
}

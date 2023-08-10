package com.ian.app.muviepedia.feature.detail.epoxy.television

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerDetailSimilarTelevisionContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerDetailSimilarTelevisionContent :
    ViewBindingEpoxyModelWithHolder<ShimmerDetailSimilarTelevisionContentBinding>() {
    override fun ShimmerDetailSimilarTelevisionContentBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerDetailSimilarTelevisionContentBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_detail_similar_television_content
    }
}
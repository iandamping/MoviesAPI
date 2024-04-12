package com.ian.app.muviepedia.feature.detail.epoxy.common

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerDetailContentBinding
import com.ian.app.muviepedia.databinding.ShimmerDetailDescriptionContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerDetailDescriptionContent :
    ViewBindingEpoxyModelWithHolder<ShimmerDetailDescriptionContentBinding>() {
    override fun ShimmerDetailDescriptionContentBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerDetailDescriptionContentBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_detail_description_content
    }
}
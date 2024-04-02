package com.ian.app.muviepedia.feature.search.television

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerItemSearchTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerSearchTelevision :
    ViewBindingEpoxyModelWithHolder<ShimmerItemSearchTelevisionBinding>() {

    override fun ShimmerItemSearchTelevisionBinding.bind() {
        shimmerSearch.startShimmer()
    }

    override fun ShimmerItemSearchTelevisionBinding.unbind() {
        shimmerSearch.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_item_search_television
    }
}

package com.ian.app.muviepedia.feature.search.epoxy

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerItemSearchMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerSearchMovie :
    ViewBindingEpoxyModelWithHolder<ShimmerItemSearchMovieBinding>() {
    override fun ShimmerItemSearchMovieBinding.bind() {
        shimmerSearch.startShimmer()
    }

    override fun ShimmerItemSearchMovieBinding.unbind() {
        shimmerSearch.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_item_search_movie
    }
}

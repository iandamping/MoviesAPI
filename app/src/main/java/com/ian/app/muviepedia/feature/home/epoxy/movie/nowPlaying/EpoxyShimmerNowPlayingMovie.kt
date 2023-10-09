package com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerItemNowPlayingMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerNowPlayingMovie :
    ViewBindingEpoxyModelWithHolder<ShimmerItemNowPlayingMovieBinding>() {
    override fun ShimmerItemNowPlayingMovieBinding.bind() {
        shimmerProduct.startShimmer()
    }

    override fun ShimmerItemNowPlayingMovieBinding.unbind() {
        shimmerProduct.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_item_now_playing_movie
    }
}

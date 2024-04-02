package com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemNowPlayingMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessNowPlayingMovie(
    private val data: EpoxyNowPlayingMovieData.MovieData,
    private val clickListener: (Int) -> Unit,
    private val viewHelper: ViewHelper
) : ViewBindingEpoxyModelWithHolder<ItemNowPlayingMovieBinding>() {

    override fun ItemNowPlayingMovieBinding.bind() {
        viewHelper.setMarginProgrammatically(
            view = viewGroupItem,
            left = 8,
            top = 8,
            right = 8,
            bottom = 8
        )
        ivMovie.load(data.posterPath) {
            crossfade(true)
            placeholder(R.drawable.empty_image)
            error(R.drawable.empty_image)
        }
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_now_playing_movie
    }
}

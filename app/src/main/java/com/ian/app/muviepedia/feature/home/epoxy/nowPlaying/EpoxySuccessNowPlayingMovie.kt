package com.ian.app.muviepedia.feature.home.epoxy.nowPlaying

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemNowPlayingBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessNowPlayingMovie(
    private val data: EpoxyNowPlayingMovieData.MovieData,
    private val clickListener: (Int) -> Unit,
    private val viewHelper: ViewHelper
) : ViewBindingEpoxyModelWithHolder<ItemNowPlayingBinding>() {

    override fun ItemNowPlayingBinding.bind() {
        viewHelper.setMarginProgrammatically(viewGroupItem, 8, 8, 8, 8)
        ivMovie.load(data.poster_path)
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }


    override fun getDefaultLayout(): Int {
        return R.layout.item_now_playing
    }
}
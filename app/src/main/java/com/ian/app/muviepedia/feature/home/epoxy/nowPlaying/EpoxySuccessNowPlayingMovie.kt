package com.ian.app.muviepedia.feature.home.epoxy.nowPlaying

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemNowPlayingBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxySuccessNowPlayingMovie(
    private val data: EpoxyNowPlayingMovieData.MovieData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemNowPlayingBinding>() {

    override fun ItemNowPlayingBinding.bind() {
        ivMovie.load(data.poster_path)
        tvMovieName.text = data.title
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }


    override fun getDefaultLayout(): Int {
        return R.layout.item_now_playing
    }
}
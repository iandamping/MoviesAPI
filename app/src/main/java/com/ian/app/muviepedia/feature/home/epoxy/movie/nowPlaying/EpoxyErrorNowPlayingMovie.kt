package com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemNowPlayingMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorNowPlayingMovie :
    ViewBindingEpoxyModelWithHolder<ErrorItemNowPlayingMovieBinding>() {
    override fun ErrorItemNowPlayingMovieBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_now_playing_movie
    }
}

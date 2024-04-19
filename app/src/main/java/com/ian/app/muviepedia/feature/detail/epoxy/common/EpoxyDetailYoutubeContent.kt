package com.ian.app.muviepedia.feature.detail.epoxy.common

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemDetailYoutubePlayerBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class EpoxyDetailYoutubeContent(
    private val youtubeId: String,
) :
    ViewBindingEpoxyModelWithHolder<ItemDetailYoutubePlayerBinding>() {
    override fun ItemDetailYoutubePlayerBinding.bind() {
        youtubeView.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(youtubeId, 0f)
            }
        })
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_detail_youtube_player
    }
}
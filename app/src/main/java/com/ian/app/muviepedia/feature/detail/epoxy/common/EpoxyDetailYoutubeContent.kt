package com.ian.app.muviepedia.feature.detail.epoxy.common

import androidx.lifecycle.LifecycleObserver
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemDetailYoutubePlayerBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback

class EpoxyDetailYoutubeContent(
    private val youtubeId: String,
    private val observer: (LifecycleObserver) -> Unit,
) : ViewBindingEpoxyModelWithHolder<ItemDetailYoutubePlayerBinding>() {
    override fun ItemDetailYoutubePlayerBinding.bind() {
        observer.invoke(youtubeView)

        youtubeView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(youtubeId, 0f)
            }
        })

    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_detail_youtube_player
    }
}
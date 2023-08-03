package com.ian.app.muviepedia.feature.home.epoxy.topRated

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemTopRatedBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessTopRatedMovie(
    private val viewHelper: ViewHelper,
    private val data: EpoxyTopRatedMovieData.MovieData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemTopRatedBinding>() {
    override fun ItemTopRatedBinding.bind() {
        viewHelper.setMarginProgrammatically(viewGroupItem, 8, 8, 8, 8)
        ivMovie.load(data.poster_path){
            crossfade(true)
            placeholder(R.drawable.empty_image)
            error(R.drawable.empty_image)
        }

        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_top_rated
    }
}
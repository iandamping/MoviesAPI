package com.ian.app.muviepedia.feature.home.epoxy.movie.upComing

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemUpComingMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessUpComingMovie(
    private val viewHelper: ViewHelper,
    private val data: EpoxyUpComingMovieData.MovieData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemUpComingMovieBinding>() {
    override fun ItemUpComingMovieBinding.bind() {
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
        return R.layout.item_up_coming_movie
    }
}

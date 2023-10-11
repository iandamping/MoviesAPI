package com.ian.app.muviepedia.feature.home.epoxy.movie.topRated

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemTopRatedMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessTopRatedMovie(
    private val viewHelper: ViewHelper,
    private val data: EpoxyTopRatedMovieData.MovieData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemTopRatedMovieBinding>() {
    override fun ItemTopRatedMovieBinding.bind() {
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
        return R.layout.item_top_rated_movie
    }
}

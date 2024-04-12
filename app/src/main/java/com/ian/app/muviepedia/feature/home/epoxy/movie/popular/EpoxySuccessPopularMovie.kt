package com.ian.app.muviepedia.feature.home.epoxy.movie.popular

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemPopularMovieBinding
import com.ian.app.muviepedia.feature.home.epoxy.data.EpoxyMovieData
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessPopularMovie(
    private val viewHelper: ViewHelper,
    private val data: EpoxyMovieData.MovieData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemPopularMovieBinding>() {
    override fun ItemPopularMovieBinding.bind() {
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
        return R.layout.item_popular_movie
    }
}

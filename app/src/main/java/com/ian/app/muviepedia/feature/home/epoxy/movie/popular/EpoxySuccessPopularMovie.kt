package com.ian.app.muviepedia.feature.home.epoxy.movie.popular

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemPopularMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessPopularMovie(
    private val viewHelper: ViewHelper,
    private val data: EpoxyPopularMovieData.MovieData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemPopularMovieBinding>() {
    override fun ItemPopularMovieBinding.bind() {
        viewHelper.setMarginProgrammatically(viewGroupItem, 8, 8, 8, 8)
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

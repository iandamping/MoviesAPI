package com.ian.app.muviepedia.feature.search.epoxy

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.databinding.ItemSearchMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessSearchMovie(
    private val data: Movie,
    private val clickListener: (Int) -> Unit,
    private val viewHelper: ViewHelper
) : ViewBindingEpoxyModelWithHolder<ItemSearchMovieBinding>() {

    override fun ItemSearchMovieBinding.bind() {
        viewHelper.setMarginProgrammatically(
            view = viewGroupItem,
            left = 8,
            top = 8,
            right = 8,
            bottom = 8
        )
        tvMovieName.text = data.originalTitle
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
        return R.layout.item_search_movie
    }
}

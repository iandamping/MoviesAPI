package com.ian.app.muviepedia.feature.detail.epoxy.movie

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.databinding.ItemDetailSimilarMovieContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyDetailSimilarMovieContent(
    private val data: Movie,
    private val clickListener: (Int) -> Unit,
    private val viewHelper: ViewHelper
) :
    ViewBindingEpoxyModelWithHolder<ItemDetailSimilarMovieContentBinding>() {
    override fun ItemDetailSimilarMovieContentBinding.bind() {
        viewHelper.setMarginProgrammatically(
            view = viewGroupItem,
            left = 8,
            top = 8,
            right = 8,
            bottom = 8
        )
        ivMovie.load(data.posterPath)
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_detail_similar_movie_content
    }
}

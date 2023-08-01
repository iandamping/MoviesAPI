package com.ian.app.muviepedia.feature.detail.epoxy

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.databinding.ItemDetailSimilarMovieContentBinding
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyDetailSimilarMovieContent(
    private val data: EpoxyMovie,
    private val clickListener: (Int) -> Unit,
    private val viewHelper: ViewHelper
) :
    ViewBindingEpoxyModelWithHolder<ItemDetailSimilarMovieContentBinding>() {
    override fun ItemDetailSimilarMovieContentBinding.bind() {
        viewHelper.setMarginProgrammatically(viewGroupItem, 8, 8, 8, 8)
        ivMovie.load(MovieConstant.imageFormatter + data.poster_path)
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_detail_similar_movie_content
    }
}
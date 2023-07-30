package com.ian.app.muviepedia.feature.home.epoxy.popular

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemPopularBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessPopularMovie(
    private val viewHelper: ViewHelper,
    private val data: EpoxyPopularMovieData.MovieData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemPopularBinding>() {
    override fun ItemPopularBinding.bind() {
        viewHelper.setMarginProgrammatically(viewGroupItem, 8, 8, 8, 8)
        ivMovie.load(data.poster_path)
        tvMovieName.text = data.title
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_popular
    }
}
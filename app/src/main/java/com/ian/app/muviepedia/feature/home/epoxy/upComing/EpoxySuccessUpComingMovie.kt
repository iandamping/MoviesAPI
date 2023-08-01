package com.ian.app.muviepedia.feature.home.epoxy.upComing

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemUpComingBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessUpComingMovie(
    private val viewHelper: ViewHelper,
    private val data: EpoxyUpComingMovieData.MovieData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemUpComingBinding>() {
    override fun ItemUpComingBinding.bind() {
        viewHelper.setMarginProgrammatically(viewGroupItem, 8, 8, 8, 8)
        ivMovie.load(data.poster_path)
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_up_coming
    }
}
package com.ian.app.muviepedia.feature.detail.epoxy

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.DetailMovieImageContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyDetailImageContent(private val imageUrl: String, private val backPressed: () -> Unit) :
    ViewBindingEpoxyModelWithHolder<DetailMovieImageContentBinding>() {
    override fun DetailMovieImageContentBinding.bind() {
        ivDetailMovie.load(imageUrl)
        ivBack.setOnClickListener { backPressed.invoke() }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.detail_movie_image_content

    }
}
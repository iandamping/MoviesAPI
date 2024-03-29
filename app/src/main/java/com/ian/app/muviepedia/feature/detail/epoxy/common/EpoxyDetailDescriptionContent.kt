package com.ian.app.muviepedia.feature.detail.epoxy.common

import android.graphics.Color
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.DetailMovieDescriptionContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyDetailDescriptionContent(private val title: String, private val description: String) :
    ViewBindingEpoxyModelWithHolder<DetailMovieDescriptionContentBinding>() {
    override fun DetailMovieDescriptionContentBinding.bind() {
        tvDetailDesc.text = description
        tvTitle.text = title
        tvDetailDesc.setTextColor(Color.WHITE)
        tvTitle.setTextColor(Color.WHITE)
    }

    override fun getDefaultLayout(): Int {
        return R.layout.detail_movie_description_content
    }
}
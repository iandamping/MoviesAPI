package com.ian.app.muviepedia.feature.home.epoxy.movie.topRated

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemTopRatedMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorTopRatedMovie : ViewBindingEpoxyModelWithHolder<ErrorItemTopRatedMovieBinding>() {
    override fun ErrorItemTopRatedMovieBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }


    override fun getDefaultLayout(): Int {
        return R.layout.error_item_top_rated_movie
    }
}
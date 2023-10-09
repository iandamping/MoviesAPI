package com.ian.app.muviepedia.feature.home.epoxy.movie.upComing

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemUpComingMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorUpComingMovie : ViewBindingEpoxyModelWithHolder<ErrorItemUpComingMovieBinding>() {
    override fun ErrorItemUpComingMovieBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_up_coming_movie
    }
}

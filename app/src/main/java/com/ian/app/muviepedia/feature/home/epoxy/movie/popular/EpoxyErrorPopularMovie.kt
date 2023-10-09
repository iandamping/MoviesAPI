package com.ian.app.muviepedia.feature.home.epoxy.movie.popular

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemPopularMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorPopularMovie : ViewBindingEpoxyModelWithHolder<ErrorItemPopularMovieBinding>() {
    override fun ErrorItemPopularMovieBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_popular_movie
    }
}

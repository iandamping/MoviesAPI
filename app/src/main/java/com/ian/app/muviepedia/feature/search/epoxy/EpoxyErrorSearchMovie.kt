package com.ian.app.muviepedia.feature.search.epoxy

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemSearchMovieBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorSearchMovie :
    ViewBindingEpoxyModelWithHolder<ErrorItemSearchMovieBinding>() {
    override fun ErrorItemSearchMovieBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_search_movie
    }
}

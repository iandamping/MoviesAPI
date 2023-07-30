package com.ian.app.muviepedia.feature.home.epoxy.topRated

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorTopRatedBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorTopRatedMovie : ViewBindingEpoxyModelWithHolder<ErrorTopRatedBinding>() {
    override fun ErrorTopRatedBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }


    override fun getDefaultLayout(): Int {
        return R.layout.error_top_rated
    }
}
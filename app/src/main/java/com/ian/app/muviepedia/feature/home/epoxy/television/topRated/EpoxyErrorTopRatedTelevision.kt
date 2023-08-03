package com.ian.app.muviepedia.feature.home.epoxy.television.topRated

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemTopRatedTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorTopRatedTelevision : ViewBindingEpoxyModelWithHolder<ErrorItemTopRatedTelevisionBinding>() {

    override fun ErrorItemTopRatedTelevisionBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_top_rated_television
    }
}
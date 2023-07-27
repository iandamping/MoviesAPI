package com.ian.app.muviepedia.feature.home.epoxy

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemPopularBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorPopularMovie : ViewBindingEpoxyModelWithHolder<ErrorItemPopularBinding>() {
    override fun ErrorItemPopularBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_popular
    }
}
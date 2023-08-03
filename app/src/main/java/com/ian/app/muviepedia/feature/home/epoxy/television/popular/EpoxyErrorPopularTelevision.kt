package com.ian.app.muviepedia.feature.home.epoxy.television.popular

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemPopularTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorPopularTelevision :
    ViewBindingEpoxyModelWithHolder<ErrorItemPopularTelevisionBinding>() {

    override fun ErrorItemPopularTelevisionBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_popular_television
    }
}
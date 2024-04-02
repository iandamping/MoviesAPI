package com.ian.app.muviepedia.feature.search.television

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorItemSearchTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorSearchTelevision :
    ViewBindingEpoxyModelWithHolder<ErrorItemSearchTelevisionBinding>() {
    override fun ErrorItemSearchTelevisionBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_search_television
    }
}

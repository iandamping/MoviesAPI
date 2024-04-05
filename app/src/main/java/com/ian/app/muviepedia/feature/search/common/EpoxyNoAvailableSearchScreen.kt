package com.ian.app.muviepedia.feature.search.common

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemNoAvailableDataBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyNoAvailableSearchScreen :
    ViewBindingEpoxyModelWithHolder<ItemNoAvailableDataBinding>() {
    override fun ItemNoAvailableDataBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.img_no_data_available))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_no_available_data
    }
}

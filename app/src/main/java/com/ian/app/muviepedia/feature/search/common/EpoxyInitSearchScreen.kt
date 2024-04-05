package com.ian.app.muviepedia.feature.search.common

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemInitSearchBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyInitSearchScreen :
    ViewBindingEpoxyModelWithHolder<ItemInitSearchBinding>() {
    override fun ItemInitSearchBinding.bind() {
        ivInit.load(AppCompatResources.getDrawable(root.context, R.drawable.img_search))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_init_search
    }
}

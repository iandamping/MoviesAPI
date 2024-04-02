package com.ian.app.muviepedia.feature.search.television

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.databinding.ItemSearchTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessSearchTelevision(
    private val data: Television,
    private val clickListener: (Int) -> Unit,
    private val viewHelper: ViewHelper
) : ViewBindingEpoxyModelWithHolder<ItemSearchTelevisionBinding>() {

    override fun ItemSearchTelevisionBinding.bind() {
        viewHelper.setMarginProgrammatically(
            view = viewGroupItem,
            left = 8,
            top = 8,
            right = 8,
            bottom = 8
        )
        tvTelevisionName.text = data.name
        ivTelevision.load(data.posterPath) {
            crossfade(true)
            placeholder(R.drawable.empty_image)
            error(R.drawable.empty_image)
        }
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_search_television
    }
}

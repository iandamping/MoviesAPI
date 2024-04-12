package com.ian.app.muviepedia.feature.detail.epoxy.companies

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemDetailSimilarContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyDetailSimilarContent(
    private val id: Int,
    private val posterPath: String,
    private val clickListener: (Int) -> Unit,
    private val viewHelper: ViewHelper
) :
    ViewBindingEpoxyModelWithHolder<ItemDetailSimilarContentBinding>() {
    override fun ItemDetailSimilarContentBinding.bind() {
        viewHelper.setMarginProgrammatically(
            view = viewGroupItem,
            left = 8,
            top = 8,
            right = 8,
            bottom = 8
        )
        ivContent.load(posterPath)
        root.setOnClickListener {
            clickListener.invoke(id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_detail_similar_content
    }
}

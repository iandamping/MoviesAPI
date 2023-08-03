package com.ian.app.muviepedia.feature.detail.epoxy.television

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevision
import com.ian.app.muviepedia.databinding.ItemDetailSimilarTelevisionContentBinding
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyDetailSimilarTelevisionContent(
    private val data: EpoxyTelevision,
    private val clickListener: (Int) -> Unit,
    private val viewHelper: ViewHelper
) :
    ViewBindingEpoxyModelWithHolder<ItemDetailSimilarTelevisionContentBinding>() {
    override fun ItemDetailSimilarTelevisionContentBinding.bind() {
        viewHelper.setMarginProgrammatically(viewGroupItem, 8, 8, 8, 8)
        ivTelevision.load(MovieConstant.imageFormatter + data.posterPath)
        root.setOnClickListener {
            clickListener.invoke(data.id)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_detail_similar_television_content
    }
}
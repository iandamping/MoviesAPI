package com.ian.app.muviepedia.feature.home.epoxy.television.popular

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemPopularTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessPopularTelevision(
    private val viewHelper: ViewHelper,
    private val data: EpoxyPopularTelevisionData.TelevisionData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemPopularTelevisionBinding>() {
    override fun ItemPopularTelevisionBinding.bind() {
        viewHelper.setMarginProgrammatically(viewGroupItem, 8, 8, 8, 8)
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
        return R.layout.item_popular_television
    }
}
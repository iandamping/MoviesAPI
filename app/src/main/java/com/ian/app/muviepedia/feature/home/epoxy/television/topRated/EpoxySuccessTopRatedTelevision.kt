package com.ian.app.muviepedia.feature.home.epoxy.television.topRated

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemTopRatedTelevisionBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySuccessTopRatedTelevision(
    private val viewHelper: ViewHelper,
    private val data: EpoxyTopRatedTelevisionData.TelevisionData,
    private val clickListener: (Int) -> Unit
) : ViewBindingEpoxyModelWithHolder<ItemTopRatedTelevisionBinding>() {

    override fun ItemTopRatedTelevisionBinding.bind() {
        viewHelper.setMarginProgrammatically(
            view = viewGroupItem,
            left = 8,
            top = 8,
            right = 8,
            bottom = 8
        )
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
        return R.layout.item_top_rated_television
    }
}

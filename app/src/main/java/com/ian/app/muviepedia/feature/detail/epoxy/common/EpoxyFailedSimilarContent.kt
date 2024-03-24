package com.ian.app.muviepedia.feature.detail.epoxy.common

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.DetailFailedSimilarContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyFailedSimilarContent :
    ViewBindingEpoxyModelWithHolder<DetailFailedSimilarContentBinding>() {
    override fun DetailFailedSimilarContentBinding.bind() {
        ivDetailFailedImage.load(R.drawable.empty_image) {
            crossfade(true)
            placeholder(R.drawable.empty_image)
            error(R.drawable.empty_image)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.detail_failed_similar_content
    }
}
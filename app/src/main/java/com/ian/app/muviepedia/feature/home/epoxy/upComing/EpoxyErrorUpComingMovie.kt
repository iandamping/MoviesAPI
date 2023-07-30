package com.ian.app.muviepedia.feature.home.epoxy.upComing

import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ErrorUpComingBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyErrorUpComingMovie : ViewBindingEpoxyModelWithHolder<ErrorUpComingBinding>() {
    override fun ErrorUpComingBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }


    override fun getDefaultLayout(): Int {
        return R.layout.error_up_coming
    }
}
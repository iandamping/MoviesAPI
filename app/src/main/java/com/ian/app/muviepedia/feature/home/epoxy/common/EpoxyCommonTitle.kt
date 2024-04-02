package com.ian.app.muviepedia.feature.home.epoxy.common

import android.graphics.Color
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.CommonTittleBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyCommonTitle(
    private val title: String,
    private val fontSize: Int,
    private val viewHelper: ViewHelper
) :
    ViewBindingEpoxyModelWithHolder<CommonTittleBinding>() {
    override fun CommonTittleBinding.bind() {
        viewHelper.setMarginProgrammatically(
            view = tvTittle,
            left = 8,
            top = 0,
            right = 8,
            bottom = 0
        )
        tvTittle.textSize = fontSize.toFloat()
        tvTittle.text = title
        tvTittle.setTextColor(Color.WHITE)
    }

    override fun getDefaultLayout(): Int {
        return R.layout.common_tittle
    }

//    turns out. it became little bigger when use this
//    private fun convertPixelToDP(viewContext: Context, dps: Int): Float {
//        val scale: Float = viewContext.resources.displayMetrics.density
//        return (dps * scale)
//    }
}

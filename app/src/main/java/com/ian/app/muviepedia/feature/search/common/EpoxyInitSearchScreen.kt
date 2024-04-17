package com.ian.app.muviepedia.feature.search.common

import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemInitSearchBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyInitSearchScreen(private val flagName: String) :
    ViewBindingEpoxyModelWithHolder<ItemInitSearchBinding>() {
    override fun ItemInitSearchBinding.bind() {
        Log.e("TAG", "bind: $flagName", )
        ivInit.load(AppCompatResources.getDrawable(root.context, R.drawable.ic_search_white_24dp))
        tvInit.text = "Search $flagName"
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_init_search
    }
}

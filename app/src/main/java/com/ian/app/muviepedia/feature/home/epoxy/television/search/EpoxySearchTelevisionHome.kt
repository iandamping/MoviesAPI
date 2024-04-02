package com.ian.app.muviepedia.feature.home.epoxy.television.search

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemSearchTelevisionHomeBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxySearchTelevisionHome(private val onSearchButtonClick: () -> Unit) :
    ViewBindingEpoxyModelWithHolder<ItemSearchTelevisionHomeBinding>() {
    override fun ItemSearchTelevisionHomeBinding.bind() {
        btnSearch.setOnClickListener { onSearchButtonClick.invoke() }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_search_television_home
    }
}
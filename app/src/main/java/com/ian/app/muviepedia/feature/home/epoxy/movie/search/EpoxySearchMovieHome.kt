package com.ian.app.muviepedia.feature.home.epoxy.movie.search

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemSearchMovieHomeBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxySearchMovieHome(private val onSearchButtonClick: () -> Unit) :
    ViewBindingEpoxyModelWithHolder<ItemSearchMovieHomeBinding>() {
    override fun ItemSearchMovieHomeBinding.bind() {
        btnSearch.setOnClickListener { onSearchButtonClick.invoke() }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_search_movie_home
    }
}
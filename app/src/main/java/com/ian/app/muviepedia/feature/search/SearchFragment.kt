package com.ian.app.muviepedia.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ian.app.muviepedia.base.BaseFragmentViewBinding
import com.ian.app.muviepedia.databinding.FragmentSearchBinding
import com.ian.app.muviepedia.di.fragmentComponent
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.search.controller.EpoxySearchController
import com.ian.app.muviepedia.util.viewHelper.ViewHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SearchFragment :
    BaseFragmentViewBinding<FragmentSearchBinding>(),
    EpoxySearchController.EpoxySearchMovieControllerListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewHelper: ViewHelper

    private val viewModel: SearchViewModel by viewModels {
        viewModelFactory
    }

    private val epoxySearchController: EpoxySearchController by lazy {
        EpoxySearchController(
            viewHelper = viewHelper,
            movieClickListener = this,
        )
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun injectingDagger() {
        fragmentComponent().injectInto(this)
    }

    override fun initView() {
        binding.rvSearchMovie.setController(epoxySearchController)
    }

    override fun viewCreated() {
        consumeSuspend {
            viewModel.epoxyMovieData.onEach { data ->
                epoxySearchController.setData(data)
            }.launchIn(this)
        }

        binding.searchViews.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!binding.searchViews.isIconified) {
                    binding.searchViews.isIconified = true
                }
                if (!query.isNullOrEmpty()) {
                    viewModel.searchMovie(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onMovieClick(id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                id,
                DetailFlag.MOVIE
            )
        )
    }
}

package com.ian.app.muviepedia.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ian.app.muviepedia.base.BaseFragmentViewBinding
import com.ian.app.muviepedia.databinding.FragmentHomeBinding
import com.ian.app.muviepedia.di.fragmentComponent
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.home.epoxy.controller.EpoxyHomeController
import com.ian.app.muviepedia.util.viewHelper.ViewHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeFragment :
    BaseFragmentViewBinding<FragmentHomeBinding>(),
    EpoxyHomeController.EpoxyMovieControllerListener,
    EpoxyHomeController.EpoxyTelevisionControllerListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewHelper: ViewHelper

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private val epoxyHomeController: EpoxyHomeController by lazy {
        EpoxyHomeController(
            viewHelper = viewHelper,
            movieClickListener = this,
            televisionClickListener = this
        )
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun injectingDagger() {
        fragmentComponent().injectInto(this)
    }

    override fun initView() {
        binding.rvHome.setController(epoxyHomeController)
    }

    override fun viewCreated() {
        consumeSuspend {
            viewModel.epoxyMovieData.onEach { data ->
                epoxyHomeController.setData(data)
            }.launchIn(this)
        }

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }
    }

    override fun onMovieClick(id: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                id,
                DetailFlag.MOVIE
            )
        )
    }

    override fun onTelevisionClick(id: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                id,
                DetailFlag.TELEVISION
            )
        )
    }
}

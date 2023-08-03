package com.ian.app.muviepedia.feature.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ian.app.muviepedia.base.BaseFragmentViewBinding
import com.ian.app.muviepedia.databinding.FragmentHomeBinding
import com.ian.app.muviepedia.di.fragmentComponent
import com.ian.app.muviepedia.feature.home.epoxy.controller.EpoxyHomeController
import com.ian.app.muviepedia.util.viewHelper.ViewHelper
import javax.inject.Inject

class HomeFragment : BaseFragmentViewBinding<FragmentHomeBinding>(),
    EpoxyHomeController.EpoxyPopularMovieControllerListener,
    EpoxyHomeController.EpoxyNowPlayingMovieControllerListener,
    EpoxyHomeController.EpoxyTopRatedMovieControllerListener,
    EpoxyHomeController.EpoxyUpComingMovieControllerListener,
    EpoxyHomeController.EpoxyPopularTelevisionControllerListener,
    EpoxyHomeController.EpoxyTopRatedTelevisionControllerListener {

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
            clickListener1 = this,
            clickListener2 = this,
            clickListener3 = this,
            clickListener4 = this,
            clickListener5 = this,
            clickListener6 = this
        )
    }


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun injectingDagger() {
        fragmentComponent().injectInto(this)
    }

    override fun initView() {
        with(binding.rvHome) {
            setController(epoxyHomeController)
        }

    }

    override fun viewCreated() {
        consumeSuspend {
            viewModel.epoxyMovieData.collect { data ->
                epoxyHomeController.setData(data)
            }
        }
    }

    override fun onPopularMovieClick(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }

    override fun onNowPlayingClick(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }

    override fun onTopRatedMovieClick(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }

    override fun onUpComingMovieClick(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }

    override fun onPopularTelevisionClick(id: Int) {
        Log.e("TAG", "onPopularTelevisionClick: $id")
    }

    override fun onTopRatedTelevisionClick(id: Int) {
        Log.e("TAG", "onTopRatedTelevisionClick: $id")
    }


}
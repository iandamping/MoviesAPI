package com.ian.app.muviepedia.feature.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ian.app.muviepedia.base.BaseFragmentViewBinding
import com.ian.app.muviepedia.databinding.FragmentHomeBinding
import com.ian.app.muviepedia.di.fragmentComponent
import com.ian.app.muviepedia.feature.home.epoxy.carousel.EpoxyHomeController
import com.ian.app.muviepedia.util.viewHelper.ViewHelper
import javax.inject.Inject

class HomeFragment : BaseFragmentViewBinding<FragmentHomeBinding>(),
    EpoxyHomeController.EpoxyPopularMovieControllerListener,
    EpoxyHomeController.EpoxyNowPlayingMovieControllerListener,
    EpoxyHomeController.EpoxyTopRatedMovieControllerListener,
    EpoxyHomeController.EpoxyUpComingMovieControllerListener {

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
            clickListener4 = this
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
        Log.e("TAG", "onPopularMovieClick: $id")
    }

    override fun onNowPlayingClick(id: Int) {
        Log.e("TAG", "onNowPlayingClick: $id")
    }

    override fun onTopRatedMovieClick(id: Int) {
        Log.e("TAG", "onTopRatedMovieClick: $id")
    }

    override fun onUpComingMovieClick(id: Int) {
        Log.e("TAG", "onUpComingMovieClick: $id")
    }


}
package com.ian.app.muviepedia.feature.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.ian.app.muviepedia.base.BaseFragmentViewBinding
import com.ian.app.muviepedia.databinding.FragmentHomeBinding
import com.ian.app.muviepedia.di.fragmentComponent
import com.ian.app.muviepedia.feature.home.epoxy.carousel.EpoxyHomeController
import javax.inject.Inject

class HomeFragment : BaseFragmentViewBinding<FragmentHomeBinding>(),
    EpoxyHomeController.EpoxyPopularMovieControllerListener,
    EpoxyHomeController.EpoxyNowPlayingMovieControllerListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private val epoxyHomeController: EpoxyHomeController by lazy {
        EpoxyHomeController(this, this)
    }


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun injectingDagger() {
        fragmentComponent().injectInto(this)
    }

    override fun initView() {
        with(binding.rvHome) {
            setController(epoxyHomeController)
            LinearSnapHelper().attachToRecyclerView(this)
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


}
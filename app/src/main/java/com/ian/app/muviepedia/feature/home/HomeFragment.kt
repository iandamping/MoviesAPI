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
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieController
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieController
import javax.inject.Inject

class HomeFragment : BaseFragmentViewBinding<FragmentHomeBinding>(),
    EpoxyPopularMovieController.EpoxyPopularMovieControllerListener,
    EpoxyNowPlayingMovieController.EpoxyNowPlayingMovieControllerListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private val epoxyPopularMovieController: EpoxyPopularMovieController by lazy {
        EpoxyPopularMovieController(this)
    }


    private val epoxyNowPlayingMovieController: EpoxyNowPlayingMovieController by lazy {
        EpoxyNowPlayingMovieController(this)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun injectingDagger() {
        fragmentComponent().injectInto(this)
    }

    override fun initView() {
        with(binding.rvPopular) {
            setController(epoxyPopularMovieController)
            LinearSnapHelper().attachToRecyclerView(this)
        }
        with(binding.rvNowPlaying) {
            setController(epoxyNowPlayingMovieController)
            LinearSnapHelper().attachToRecyclerView(this)
        }
    }

    override fun viewCreated() {
        consumeSuspend {
            viewModel.epoxyPopularMovieData.collect { data ->
                epoxyPopularMovieController.setData(data)
            }
        }
        consumeSuspend {
            viewModel.epoxyNowPlayingMovieData.collect { data ->
                epoxyNowPlayingMovieController.setData(data)
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
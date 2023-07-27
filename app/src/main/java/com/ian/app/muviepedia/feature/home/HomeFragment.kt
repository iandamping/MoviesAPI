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
import com.ian.app.muviepedia.feature.home.epoxy.EpoxyPopularMovieController
import com.ian.app.muviepedia.feature.state.PresentationState
import javax.inject.Inject

class HomeFragment : BaseFragmentViewBinding<FragmentHomeBinding>(),
    EpoxyPopularMovieController.EpoxyPopularMovieControllerListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private val epoxyMultiProductHomeController: EpoxyPopularMovieController by lazy {
        EpoxyPopularMovieController(this)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun injectingDagger() {
        fragmentComponent().injectInto(this)
    }

    override fun initView() {
        with(binding.rvHome) {
            setController(epoxyMultiProductHomeController)
            LinearSnapHelper().attachToRecyclerView(this)
        }
    }

    override fun viewCreated() {
        consumeSuspend {
            viewModel.popularMovieUiState.collect { data ->
                when (data.uiState) {
                    PresentationState.Loading -> viewModel.setEpoxyLoading()
                    PresentationState.Success -> {
                        if (data.data.isNotEmpty()) {
                            viewModel.setEpoxyData(data.data)
                        } else {
                            viewModel.setEpoxyError()
                        }
                    }

                    PresentationState.Failed -> viewModel.setEpoxyError()
                }
            }
        }

        consumeSuspend {
            viewModel.epoxyPopularMovieData.collect { data ->
                epoxyMultiProductHomeController.setData(data)
            }
        }
    }


    override fun onMovieClick(id: Int) {
        Log.e("TAG", "onMovieClick id: $id", )
    }
}
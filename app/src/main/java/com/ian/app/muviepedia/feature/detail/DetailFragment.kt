package com.ian.app.muviepedia.feature.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.base.BaseFragmentViewBinding
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant
import com.ian.app.muviepedia.databinding.FragmentDetailBinding
import com.ian.app.muviepedia.di.fragmentComponent
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.detail.epoxy.controller.EpoxyDetailController
import com.ian.app.muviepedia.feature.state.PresentationState
import com.ian.app.muviepedia.util.viewHelper.ViewHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailFragment :
    BaseFragmentViewBinding<FragmentDetailBinding>(),
    EpoxyDetailController.EpoxyDetailControllerBackPress,
    EpoxyDetailController.EpoxyDetailControllerSimilarItemListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewHelper: ViewHelper

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        viewModelFactory
    }
    private val epoxyDetailController: EpoxyDetailController by lazy {
        EpoxyDetailController(
            similarMovieListener = this,
            backPressListener = this,
            viewHelper = viewHelper
        )
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun injectingDagger() {
        fragmentComponent().injectInto(this)
    }

    override fun initView() {
        viewModel.getDetailMovie(args.passedMovieId, args.passedDetailFlag)
        with(binding) {
            rvDetail.setController(epoxyDetailController)
        }
    }

    override fun viewCreated() {
        consumeSuspend {
            launch {
                viewModel.detailSimilarDataUiState.onEach { data ->
                    epoxyDetailController.setData(data)
                }.launchIn(this)
            }

            launch {
                viewModel.detailDataUiState.onEach { data ->
                    when (data.uiState) {
                        PresentationState.Loading -> with(binding) {
                            shimmerImage.shimmerProduct.startShimmer()
                        }

                        PresentationState.Success -> when (data.flag) {
                            DetailFlag.MOVIE -> {
                                if (data.movieData != null) {
                                    with(binding) {
                                        ivDetailMovie.load(NetworkConstant.imageFormatter + data.movieData.backdropPath) {
                                            crossfade(true)
                                            placeholder(R.drawable.empty_image)
                                            error(R.drawable.empty_image)
                                        }
                                        shimmerImage.shimmerProduct.stopShimmer()
                                        shimmerImage.shimmerProduct.visibility = View.GONE
                                        tvDetailTitle.text = data.movieData.title
                                        tvDetailDesc.text = data.movieData.overview
                                        ivBack.setOnClickListener { findNavController().popBackStack() }
                                    }
                                } else return@onEach
                            }

                            DetailFlag.TELEVISION -> {
                                if (data.televisionData != null) {
                                    with(binding) {
                                        ivDetailMovie.load(NetworkConstant.imageFormatter + data.televisionData.backdropPath) {
                                            crossfade(true)
                                            placeholder(R.drawable.empty_image)
                                            error(R.drawable.empty_image)
                                        }
                                        shimmerImage.shimmerProduct.stopShimmer()
                                        shimmerImage.shimmerProduct.visibility = View.GONE
                                        tvDetailTitle.text = data.televisionData.title
                                        tvDetailDesc.text = data.televisionData.overview
                                        ivBack.setOnClickListener { findNavController().popBackStack() }
                                    }
                                } else return@onEach
                            }
                        }

                        PresentationState.Failed -> {
                            binding.shimmerImage.shimmerProduct.stopShimmer()
                            binding.shimmerImage.shimmerProduct.visibility = View.GONE
                        }
                    }


                }.launchIn(this)
            }

        }
    }

    override fun onBackIsPressed() {
        findNavController().popBackStack()
    }

    override fun onSimilarItemClick(id: Int) {
        viewModel.getDetailMovie(id, args.passedDetailFlag)
    }
}

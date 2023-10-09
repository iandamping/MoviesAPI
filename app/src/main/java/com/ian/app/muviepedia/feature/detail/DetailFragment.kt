package com.ian.app.muviepedia.feature.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ian.app.muviepedia.base.BaseFragmentViewBinding
import com.ian.app.muviepedia.databinding.FragmentDetailBinding
import com.ian.app.muviepedia.di.fragmentComponent
import com.ian.app.muviepedia.feature.detail.epoxy.controller.EpoxyDetailController
import com.ian.app.muviepedia.util.viewHelper.ViewHelper
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
        with(binding.rvDetail) {
            setController(epoxyDetailController)
        }
    }

    override fun viewCreated() {
        consumeSuspend {
            viewModel.detailMovieUiState.collect {
                epoxyDetailController.setData(it)
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

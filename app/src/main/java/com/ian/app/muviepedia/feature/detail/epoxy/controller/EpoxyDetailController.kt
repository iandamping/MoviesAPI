package com.ian.app.muviepedia.feature.detail.epoxy.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyFailedSimilarContent
import com.ian.app.muviepedia.feature.detail.epoxy.movie.EpoxyDetailSimilarMovieContent
import com.ian.app.muviepedia.feature.detail.epoxy.movie.EpoxyShimmerDetailSimilarMovieContent
import com.ian.app.muviepedia.feature.detail.epoxy.television.EpoxyDetailSimilarTelevisionContent
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.state.DetailSimilarDataUIState
import com.ian.app.muviepedia.feature.state.PresentationState
import com.ian.app.muviepedia.util.epoxy.ViewBindingHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyDetailController(
    private val similarMovieListener: EpoxyDetailControllerSimilarItemListener,
    private val viewHelper: ViewHelper
) :
    TypedEpoxyController<DetailSimilarDataUIState>() {

    interface EpoxyDetailControllerBackPress {
        fun onBackIsPressed()
    }

    interface EpoxyDetailControllerSimilarItemListener {
        fun onSimilarItemClick(id: Int)
    }

    override fun buildModels(data: DetailSimilarDataUIState?) {
        // epoxyModel id is the one that control the view
        // if the view is static use static string to epoxyModel id
        // if the view is dynamic use their data as epoxyModel id
        if (data != null) {
            when (data.uiState) {
                PresentationState.Loading -> epoxyLoading()

                PresentationState.Success -> epoxySuccess(data)

                PresentationState.Failed -> epoxyFailed()
            }
        }
    }

    private fun epoxySuccess(data: DetailSimilarDataUIState) {
        when (data.flag) {
            DetailFlag.MOVIE -> {
                if (data.similarMovieData.isNotEmpty()) {
                    epoxySimilarMovie(data)
                }
            }

            DetailFlag.TELEVISION -> {
                if (data.similarTelevisionData.isNotEmpty()) {
                    epoxySimilarTelevision(data)
                }
            }
        }
    }

    private fun epoxySimilarTelevision(data: DetailSimilarDataUIState) {
        EpoxyCommonTitle(
            title = "Similar Television",
            fontSize = 24,
            viewHelper = viewHelper
        ).id("detail_similar_television_title").addTo(this)

        val carouselSimilarTelevisionModel =
            data.similarTelevisionData.map {
                EpoxyDetailSimilarTelevisionContent(
                    data = it,
                    viewHelper = viewHelper,
                    clickListener = similarMovieListener::onSimilarItemClick
                ).id(it.epoxyId)
            }

        CarouselModel_().id("3_similar_content")
            .models(carouselSimilarTelevisionModel)
            .numViewsToShowOnScreen(2f).addTo(this)
    }

    private fun epoxySimilarMovie(data: DetailSimilarDataUIState) {
        EpoxyCommonTitle(
            title = "Similar Movie",
            fontSize = 24,
            viewHelper = viewHelper
        ).id("detail_similar_movie_title").addTo(this)

        val carouselSimilarMovieModel = data.similarMovieData.map {
            EpoxyDetailSimilarMovieContent(
                data = it,
                viewHelper = viewHelper,
                clickListener = similarMovieListener::onSimilarItemClick
            ).id(it.epoxyId)
        }

        CarouselModel_().id("3_similar_content")
            .models(carouselSimilarMovieModel).numViewsToShowOnScreen(2f)
            .addTo(this)
    }

    private fun epoxyLoading() {
        val shimmerModel: MutableList<EpoxyModel<ViewBindingHolder>> = mutableListOf()

        for (i in startLoop..endLoop) {
            shimmerModel.add(
                EpoxyShimmerDetailSimilarMovieContent().id(i)
            )
        }

        CarouselModel_().id("3_similar_content").models(shimmerModel).numViewsToShowOnScreen(2f)
            .addTo(this)
    }

    private fun epoxyFailed() {
        EpoxyFailedSimilarContent().id("3_similar_content").addTo(this)
    }

    companion object {
        private const val startLoop = 4
        private const val endLoop = 12
    }
}

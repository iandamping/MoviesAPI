package com.ian.app.muviepedia.feature.detail.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.state.DetailMovieUiState
import com.ian.app.muviepedia.feature.state.PresentationState
import com.ian.app.muviepedia.util.MovieConstant.imageFormatter
import com.ian.app.muviepedia.util.epoxy.ViewBindingHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyDetailController(
    private val backPressListener: EpoxyDetailControllerBackPress,
    private val similarMovieListener: EpoxyDetailControllerSimilarItemListener,
    private val viewHelper: ViewHelper
) :
    TypedEpoxyController<DetailMovieUiState>() {

    interface EpoxyDetailControllerBackPress {
        fun onBackIsPressed()
    }

    interface EpoxyDetailControllerSimilarItemListener {
        fun onSimilarItemClick(movieId: Int)
    }

    override fun buildModels(data: DetailMovieUiState?) {
        // epoxyModel id is the one that control the view
        // if the view is static use static string to epoxyModel id
        // if the view is dynamic use their data as epoxyModel id
        if (data != null) {
            if (data.uiState == PresentationState.Loading) {
                EpoxyShimmerDetailImageContent()
                    .id("0")
                    .addTo(this)

                EpoxyShimmerDetailDescriptionContent()
                    .id("1")
                    .addTo(this)
            }
            if (data.data != null) {
                EpoxyDetailImageContent(imageUrl = imageFormatter + data.data.backdrop_path) {
                    backPressListener.onBackIsPressed()
                }.id(data.data.backdrop_path)
                    .addTo(this)

                EpoxyDetailDescriptionContent(
                    description = data.data.overview,
                    title = data.data.title
                )
                    .id(data.data.overview)
                    .addTo(this)
            }
            EpoxyCommonTitle("Similar Movie", 24)
                .id("detail_similar_movie_title")
                .addTo(this)

            if (data.uiState == PresentationState.Loading) {
                val shimmerModel: MutableList<EpoxyModel<ViewBindingHolder>> =
                    mutableListOf()

                for (i in 4..12) {
                    shimmerModel.add(
                        EpoxyShimmerDetailSimilarMovieContent()
                            .id(i)
                    )
                }

                CarouselModel_()
                    .id("3")
                    .models(shimmerModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }

            if (data.similarData.isNotEmpty()) {
                val carouselSimilarMovieModel = data.similarData.map {
                    EpoxyDetailSimilarMovieContent(
                        data = it,
                        viewHelper = viewHelper,
                        clickListener = similarMovieListener::onSimilarItemClick
                    ).id(it.epoxyId)
                }

                CarouselModel_()
                    .id(data.data?.id)
                    .models(carouselSimilarMovieModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }

        }
    }
}
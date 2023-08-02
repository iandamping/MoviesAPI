package com.ian.app.muviepedia.feature.detail.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.state.DetailMovieUiState
import com.ian.app.muviepedia.util.MovieConstant.imageFormatter
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
        // if the view is dynamic use their data as epoxyModel id
        if (data != null) {
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
            // if the view is static use static string to epoxyModel id
            EpoxyCommonTitle("Similar Movie", 24)
                .id("detail_similar_movie_title")
                .addTo(this)

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
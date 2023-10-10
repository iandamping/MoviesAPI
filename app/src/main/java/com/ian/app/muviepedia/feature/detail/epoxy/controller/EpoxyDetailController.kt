package com.ian.app.muviepedia.feature.detail.epoxy.controller

import android.util.Log
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.imageFormatter
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyDetailDescriptionContent
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyDetailImageContent
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyShimmerDetailDescriptionContent
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyShimmerDetailImageContent
import com.ian.app.muviepedia.feature.detail.epoxy.movie.EpoxyDetailSimilarMovieContent
import com.ian.app.muviepedia.feature.detail.epoxy.movie.EpoxyShimmerDetailSimilarMovieContent
import com.ian.app.muviepedia.feature.detail.epoxy.television.EpoxyDetailSimilarTelevisionContent
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.state.DetailMovieUiState
import com.ian.app.muviepedia.feature.state.PresentationState
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
        fun onSimilarItemClick(id: Int)
    }

    override fun buildModels(data: DetailMovieUiState?) {
        // epoxyModel id is the one that control the view
        // if the view is static use static string to epoxyModel id
        // if the view is dynamic use their data as epoxyModel id
        if (data != null) {
            when (data.uiState) {
                PresentationState.Loading -> {
                    val shimmerModel: MutableList<EpoxyModel<ViewBindingHolder>> = mutableListOf()

                    for (i in 4..12) {
                        shimmerModel.add(
                            EpoxyShimmerDetailSimilarMovieContent().id(i)
                        )
                    }

                    EpoxyShimmerDetailImageContent().id("0").addTo(this)

                    EpoxyShimmerDetailDescriptionContent().id("1").addTo(this)

                    CarouselModel_().id("3").models(shimmerModel).numViewsToShowOnScreen(2f)
                        .addTo(this)
                }

                PresentationState.Success -> {
                    when (data.flag) {
                        DetailFlag.MOVIE -> {
                            if (data.movieData != null) {
                                EpoxyDetailImageContent(imageUrl = imageFormatter + data.movieData.backdropPath) {
                                    backPressListener.onBackIsPressed()
                                }.id(data.movieData.backdropPath).addTo(this)

                                EpoxyDetailDescriptionContent(
                                    description = data.movieData.overview,
                                    title = data.movieData.title
                                ).id(data.movieData.overview).addTo(this)
                            }

                            if (data.similarMovieData.isNotEmpty()) {
                                EpoxyCommonTitle(
                                    title = "Similar Movie",
                                    fontSize = 24
                                ).id("detail_similar_movie_title").addTo(this)

                                val carouselSimilarMovieModel = data.similarMovieData.map {
                                    EpoxyDetailSimilarMovieContent(
                                        data = it,
                                        viewHelper = viewHelper,
                                        clickListener = similarMovieListener::onSimilarItemClick
                                    ).id(it.epoxyId)
                                }

                                CarouselModel_().id(data.movieData?.id)
                                    .models(carouselSimilarMovieModel).numViewsToShowOnScreen(2f)
                                    .addTo(this)
                            }
                        }

                        DetailFlag.TELEVISION -> {
                            if (data.televisionData != null) {
                                EpoxyDetailImageContent(imageUrl = imageFormatter + data.televisionData.backdropPath) {
                                    backPressListener.onBackIsPressed()
                                }.id(data.televisionData.backdropPath).addTo(this)

                                EpoxyDetailDescriptionContent(
                                    description = data.televisionData.overview,
                                    title = data.televisionData.title
                                ).id(data.televisionData.overview).addTo(this)
                            }

                            if (data.similarTelevisionData.isNotEmpty()) {
                                EpoxyCommonTitle(
                                    title = "Similar Television",
                                    fontSize = 24
                                ).id("detail_similar_television_title").addTo(this)

                                val carouselSimilarTelevisionModel =
                                    data.similarTelevisionData.map {
                                        EpoxyDetailSimilarTelevisionContent(
                                            data = it,
                                            viewHelper = viewHelper,
                                            clickListener = similarMovieListener::onSimilarItemClick
                                        ).id(it.epoxyId)
                                    }

                                CarouselModel_().id(data.televisionData?.id)
                                    .models(carouselSimilarTelevisionModel)
                                    .numViewsToShowOnScreen(2f).addTo(this)
                            }
                        }
                    }
                }

                PresentationState.Failed -> Log.e("TAG", "buildModels: ${data.errorMessage}")
            }
        }
    }
}

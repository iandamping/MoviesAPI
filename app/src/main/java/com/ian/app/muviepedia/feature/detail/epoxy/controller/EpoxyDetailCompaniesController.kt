package com.ian.app.muviepedia.feature.detail.epoxy.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyFailedSimilarContent
import com.ian.app.muviepedia.feature.detail.epoxy.companies.EpoxyMovieDetailCompanyContent
import com.ian.app.muviepedia.feature.detail.epoxy.companies.EpoxyShimmerDetailCompanyContent
import com.ian.app.muviepedia.feature.detail.epoxy.companies.EpoxyTvDetailCompanyContent
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.state.DetailCompanyDataUiState
import com.ian.app.muviepedia.feature.state.PresentationState
import com.ian.app.muviepedia.util.epoxy.ViewBindingHolder
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxyDetailCompaniesController(private val viewHelper: ViewHelper) :
    TypedEpoxyController<DetailCompanyDataUiState>() {

    override fun buildModels(data: DetailCompanyDataUiState?) {
        if (data != null) {
            when (data.uiState) {
                PresentationState.Loading -> epoxyLoading()

                PresentationState.Success -> epoxySuccess(data)

                PresentationState.Failed -> epoxyFailed()
            }
        }
    }


    private fun epoxySuccess(data: DetailCompanyDataUiState) {
        when (data.flag) {
            DetailFlag.MOVIE -> {
                if (data.movieCompany.isNotEmpty()) {
                    epoxyCompanyMovie(data.movieCompany)
                }
            }

            DetailFlag.TELEVISION -> {
                if (data.televisionCompany.isNotEmpty()) {
                    epoxyCompanyTelevision(data.televisionCompany)
                }
            }
        }
    }

    private fun epoxyCompanyTelevision(data: List<TelevisionDetail.ProductionCompany>) {
        val companyTvModel = data.map {
            EpoxyTvDetailCompanyContent(
                data = it,
            ).id(it.id)
        }

        CarouselModel_().id("1")
            .models(companyTvModel)
            .numViewsToShowOnScreen(1.5f).addTo(this)
    }

    private fun epoxyCompanyMovie(data: List<MovieDetail.ProductionCompany>) {
       val companyMovieModel =  data.map {
            EpoxyMovieDetailCompanyContent(
                data = it,
            ).id(it.id)
        }

        EpoxyCommonTitle(title = "Companies", fontSize = 16, viewHelper = viewHelper)
            .id("0")
            .addTo(this)

        CarouselModel_().id("1")
            .models(companyMovieModel)
            .numViewsToShowOnScreen(1.5f).addTo(this)
    }

    private fun epoxyLoading() {
        val listOfLoadingModel:MutableList<EpoxyModel<ViewBindingHolder>> = mutableListOf()
        for (i in 0..2) {
            listOfLoadingModel.add(EpoxyShimmerDetailCompanyContent().id(i))

        }

        CarouselModel_()
            .id("1")
            .models(listOfLoadingModel)
            .numViewsToShowOnScreen(1.5f)
            .addTo(this)
    }

    private fun epoxyFailed() {
        EpoxyFailedSimilarContent().id("1").addTo(this)
    }
}
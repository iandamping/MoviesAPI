package com.ian.app.muviepedia.feature.detail.epoxy.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyDetailImageContent
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyFailedDataContent
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyFailedSimilarContent
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyShimmerDetailDescriptionContent
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyShimmerDetailImageContent
import com.ian.app.muviepedia.feature.detail.epoxy.common.EpoxyShimmerDetailSimilarContent
import com.ian.app.muviepedia.feature.detail.epoxy.companies.EpoxyDetailCompanyContent
import com.ian.app.muviepedia.feature.detail.epoxy.companies.EpoxyDetailSimilarContent
import com.ian.app.muviepedia.feature.detail.epoxy.companies.EpoxyShimmerDetailCompanyContent
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailImageData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData
import com.ian.app.muviepedia.feature.detail.epoxy.movie.EpoxyDetailDescriptionMovieContent
import com.ian.app.muviepedia.feature.detail.epoxy.television.EpoxyDetailDescriptionTelevisionContent
import com.ian.app.muviepedia.feature.home.epoxy.common.EpoxyCommonTitle
import com.ian.app.muviepedia.feature.state.DetailDataUiState
import com.ian.app.muviepedia.util.viewHelper.ViewHelper
import java.util.UUID

class EpoxyDetailController(
    private val backPressListener: EpoxyDetailControllerBackPress,
    private val similarMovieListener: EpoxyDetailControllerSimilarItemListener,
    private val viewHelper: ViewHelper
) :
    TypedEpoxyController<DetailDataUiState>() {

    interface EpoxyDetailControllerBackPress {
        fun onBackIsPressed()
    }

    interface EpoxyDetailControllerSimilarItemListener {
        fun onSimilarItemClick(id: Int)
    }

    override fun buildModels(data: DetailDataUiState?) {
        // epoxyModel id is the one that control the view
        // if the view is static use static string to epoxyModel id
        // if the view is dynamic use their data as epoxyModel id
        if (data != null) {

            implementEpoxyDetailImageContent(data)

            implementEpoxyDetailContent(data)

            implementEpoxyDetailCompany(data)

            implementEpoxyDetailSimilar(data)

        }
    }


    private fun implementEpoxyDetailImageContent(data: DetailDataUiState) {
        if (data.imageData != null) {

            val carouselImageModel = when (data.imageData) {
                EpoxyDetailImageData.Error -> EpoxyFailedDataContent().id(
                    UUID.randomUUID().toString()
                )

                is EpoxyDetailImageData.ImageData -> EpoxyDetailImageContent(imageUrl = data.imageData.image) {
                    backPressListener.onBackIsPressed()
                }.id(data.imageData.epoxyImageId)

                is EpoxyDetailImageData.Shimmer -> EpoxyShimmerDetailImageContent().id(data.imageData.epoxyId)
            }

            CarouselModel_().id("1_content_image")
                .models(mutableListOf(carouselImageModel))
                .numViewsToShowOnScreen(1f).addTo(this)
        }
    }

    private fun implementEpoxyDetailContent(data: DetailDataUiState) {
        if (data.contentData != null) {

            val carouselCompanyModel = when (data.contentData) {

                is EpoxyDetailContentData.Error -> EpoxyFailedDataContent().id(
                    UUID.randomUUID().toString()
                )

                is EpoxyDetailContentData.MovieData -> EpoxyDetailDescriptionMovieContent(
                    title = data.contentData.title,
                    tagline = data.contentData.tagline,
                    overview = data.contentData.overview,
                    voteAverage = data.contentData.voteAverage,
                    releaseDate = data.contentData.releaseDate,
                    revenue = data.contentData.revenue,
                ).id(data.contentData.epoxyContentId)


                is EpoxyDetailContentData.Shimmer -> EpoxyShimmerDetailDescriptionContent().id(data.contentData.epoxyId)

                is EpoxyDetailContentData.TelevisionData -> EpoxyDetailDescriptionTelevisionContent(
                    title = data.contentData.title,
                    tagline = data.contentData.tagline,
                    overview = data.contentData.overview,
                    voteAverage = data.contentData.voteAverage,
                    firstAiringDate = data.contentData.firstAiringDate,
                    lastAiringDate = data.contentData.lastAiringDate,
                    numberOfSeasons = data.contentData.numberOfSeasons,
                    numberOfEpisodes = data.contentData.numberOfEpisodes,
                ).id(data.contentData.epoxyContentId)

            }

            CarouselModel_().id("2_content_movie")
                .models(mutableListOf(carouselCompanyModel))
                .numViewsToShowOnScreen(1f).addTo(this)
        }
    }

    private fun implementEpoxyDetailCompany(data: DetailDataUiState) {
        if (data.companyData.isNotEmpty()) {
            EpoxyCommonTitle(title = "Companies", fontSize = 16, viewHelper = viewHelper)
                .id("3_company_title_movie")
                .addTo(this)

            val carouselCompanyModel = data.companyData.map { multiData ->
                when (multiData) {
                    is EpoxyDetailCompanyData.CompanyData -> {
                        EpoxyDetailCompanyContent(
                            data = multiData,
                        ).id(multiData.id)
                    }

                    is EpoxyDetailCompanyData.Error -> EpoxyFailedSimilarContent().id(
                        UUID.randomUUID().toString()
                    )

                    is EpoxyDetailCompanyData.Shimmer -> EpoxyShimmerDetailCompanyContent().id(
                        multiData.epoxyId
                    )
                }
            }

            CarouselModel_().id("3_company_movie")
                .models(carouselCompanyModel)
                .numViewsToShowOnScreen(1.5f).addTo(this)
        } else {
            //todo: create this
            EpoxyFailedSimilarContent().id(
                UUID.randomUUID().toString()
            ).addTo(this)
        }
    }

    private fun implementEpoxyDetailSimilar(data: DetailDataUiState) {
        if (data.similarData.isNotEmpty()) {
            EpoxyCommonTitle(
                title = "Similar Item",
                fontSize = 24,
                viewHelper = viewHelper
            ).id("4_title_similar_item").addTo(this)

            val carouselCompanyModel = data.similarData.map { multiData ->
                when (multiData) {
                    is EpoxyDetailSimilarData.SimilarData -> {
                        EpoxyDetailSimilarContent(
                            id = multiData.dataId,
                            posterPath = multiData.image,
                            viewHelper = viewHelper,
                            clickListener = similarMovieListener::onSimilarItemClick
                        ).id(multiData.dataId)
                    }

                    is EpoxyDetailSimilarData.Error -> EpoxyFailedSimilarContent().id(
                        UUID.randomUUID().toString()
                    )

                    is EpoxyDetailSimilarData.Shimmer -> EpoxyShimmerDetailSimilarContent().id(
                        multiData.epoxyId
                    )
                }
            }

            CarouselModel_().id("4_similar_data")
                .models(carouselCompanyModel)
                .numViewsToShowOnScreen(2.5f).addTo(this)
        } else {
            //todo: create this
            EpoxyFailedSimilarContent().id(
                UUID.randomUUID().toString()
            ).addTo(this)
        }
    }

}

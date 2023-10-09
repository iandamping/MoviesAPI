package com.ian.app.muviepedia.feature.search.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.feature.search.epoxy.EpoxyErrorSearchMovie
import com.ian.app.muviepedia.feature.search.epoxy.EpoxyShimmerSearchMovie
import com.ian.app.muviepedia.feature.search.epoxy.EpoxySuccessSearchMovie
import com.ian.app.muviepedia.util.epoxy.HorizontalGridCarouselModel_
import com.ian.app.muviepedia.util.epoxy.VerticalGridCarouselModel_
import com.ian.app.muviepedia.util.viewHelper.ViewHelper

class EpoxySearchController(
    private val viewHelper: ViewHelper,
    private val movieClickListener: EpoxySearchMovieControllerListener,
) : TypedEpoxyController<EpoxySearchData>() {

    interface EpoxySearchMovieControllerListener {
        fun onMovieClick(id: Int)
    }

    override fun buildModels(data: EpoxySearchData?) {
        // carousel search movie
        if (data != null) {
            if (data.searchMovie.isNotEmpty()) {
                val carouselSearchModel = data.searchMovie.map { multiData ->
                    EpoxySuccessSearchMovie(
                        viewHelper = viewHelper,
                        data = multiData,
                        clickListener = movieClickListener::onMovieClick
                    ).id(multiData.id)
                }

                VerticalGridCarouselModel_()
                    .id("1")
                    .models(carouselSearchModel)
                    .numViewsToShowOnScreen(5f)
                    .addTo(this)
            }

            if (data.loading.isNotEmpty()) {
                val loadingModel = data.loading.map { loadingData ->
                    EpoxyShimmerSearchMovie()
                        .id(loadingData)
                }
                VerticalGridCarouselModel_()
                    .id("1")
                    .models(loadingModel)
                    .numViewsToShowOnScreen(2f)
                    .addTo(this)
            }

            if (data.error.isNotEmpty()) {
                val errorModel = data.error.map { loadingData ->
                    EpoxyErrorSearchMovie()
                        .id(loadingData)
                }
                HorizontalGridCarouselModel_()
                    .id("1")
                    .models(errorModel)
                    .numViewsToShowOnScreen(1f)
                    .addTo(this)
            }
        }
    }
}

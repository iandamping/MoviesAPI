package com.ian.app.muviepedia.feature.detail.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.util.MovieConstant.imageFormatter

class EpoxyDetailController(private val backPressListener: EpoxyDetailControllerBackPress) :
    TypedEpoxyController<MovieDetail>() {

    interface EpoxyDetailControllerBackPress {
        fun onBackIsPressed()
    }

    override fun buildModels(data: MovieDetail?) {
        if (data != null) {
            EpoxyDetailImageContent(imageUrl = imageFormatter + data.backdrop_path) {
                backPressListener.onBackIsPressed()
            }.id("detail_image")
                .addTo(this)

            EpoxyDetailDescriptionContent(description = data.overview)
                .id("detail_description")
                .addTo(this)
        }
    }
}
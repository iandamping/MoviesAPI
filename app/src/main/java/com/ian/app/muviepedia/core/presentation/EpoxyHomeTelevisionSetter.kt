package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData

interface EpoxyHomeTelevisionSetter {

    fun setEpoxyPopularTelevisionData(televisionData: List<Television>): List<EpoxyPopularTelevisionData.TelevisionData>

    fun setEpoxyPopularTelevisionLoading(): List<EpoxyPopularTelevisionData.Shimmer>

    fun setEpoxyPopularTelevisionError(): List<EpoxyPopularTelevisionData.Error>

    fun setEpoxyTopRatedTelevisionData(televisionData: List<Television>): List<EpoxyTopRatedTelevisionData.TelevisionData>

    fun setEpoxyTopRatedTelevisionLoading(): List<EpoxyTopRatedTelevisionData.Shimmer>

    fun setEpoxyTopRatedTelevisionError(): List<EpoxyTopRatedTelevisionData.Error>
}

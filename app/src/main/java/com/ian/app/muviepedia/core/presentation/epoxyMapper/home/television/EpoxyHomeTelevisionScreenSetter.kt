package com.ian.app.muviepedia.core.presentation.epoxyMapper.home.television

import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.feature.home.epoxy.television.data.EpoxyTelevisionData

interface EpoxyHomeTelevisionScreenSetter {

    fun setEpoxyPopularTelevisionData(televisionData: List<Television>): List<EpoxyTelevisionData.TelevisionData>

    fun setEpoxyPopularTelevisionLoading(): List<EpoxyTelevisionData.Shimmer>

    fun setEpoxyPopularTelevisionError(): List<EpoxyTelevisionData.Error>

    fun setEpoxyTopRatedTelevisionData(televisionData: List<Television>): List<EpoxyTelevisionData.TelevisionData>

    fun setEpoxyTopRatedTelevisionLoading(): List<EpoxyTelevisionData.Shimmer>

    fun setEpoxyTopRatedTelevisionError(): List<EpoxyTelevisionData.Error>
}

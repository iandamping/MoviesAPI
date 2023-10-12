package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData
import javax.inject.Inject

class EpoxyHomeTelevisionSetterImpl @Inject constructor(private val epoxyMapper: EpoxyMapper) :
    EpoxyHomeTelevisionSetter {

    override fun setEpoxyPopularTelevisionData(televisionData: List<Television>): List<EpoxyPopularTelevisionData.TelevisionData> {
        return epoxyMapper.epoxyPopularTVListMapper(
            epoxyMapper.extractTelevisionToEpoxy(
                televisionData
            )
        )
    }

    override fun setEpoxyPopularTelevisionLoading(): List<EpoxyPopularTelevisionData.Shimmer> {
        return mutableListOf(
            EpoxyPopularTelevisionData.Shimmer(epoxyId = 0),
            EpoxyPopularTelevisionData.Shimmer(epoxyId = 1),
            EpoxyPopularTelevisionData.Shimmer(epoxyId = 2),
            EpoxyPopularTelevisionData.Shimmer(epoxyId = 3),
            EpoxyPopularTelevisionData.Shimmer(epoxyId = 4),
            EpoxyPopularTelevisionData.Shimmer(epoxyId = 5),
            EpoxyPopularTelevisionData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyPopularTelevisionError(): List<EpoxyPopularTelevisionData.Error> {
        return mutableListOf(EpoxyPopularTelevisionData.Error)
    }

    override fun setEpoxyTopRatedTelevisionData(televisionData: List<Television>): List<EpoxyTopRatedTelevisionData.TelevisionData> {
        return epoxyMapper.epoxyTopRatedTvListMapper(
            epoxyMapper.extractTelevisionToEpoxy(
                televisionData
            )
        )
    }

    override fun setEpoxyTopRatedTelevisionLoading(): List<EpoxyTopRatedTelevisionData.Shimmer> {
        return mutableListOf(
            EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 0),
            EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 1),
            EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 2),
            EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 3),
            EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 4),
            EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 5),
            EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyTopRatedTelevisionError(): List<EpoxyTopRatedTelevisionData.Error> {
        return mutableListOf(EpoxyTopRatedTelevisionData.Error)
    }
}

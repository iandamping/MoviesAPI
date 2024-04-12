package com.ian.app.muviepedia.core.presentation.epoxyMapper.home.television

import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.television.EpoxyTelevisionMapper
import com.ian.app.muviepedia.feature.home.epoxy.television.data.EpoxyTelevisionData
import javax.inject.Inject

class EpoxyHomeTelevisionScreenSetterImpl @Inject constructor(private val epoxyTelevisionMapper: EpoxyTelevisionMapper) :
    EpoxyHomeTelevisionScreenSetter {

    override fun setEpoxyPopularTelevisionData(televisionData: List<Television>): List<EpoxyTelevisionData.TelevisionData> {
        return epoxyTelevisionMapper.epoxyPopularTVListMapper(
            epoxyTelevisionMapper.extractTelevisionToEpoxy(
                televisionData
            )
        )
    }

    override fun setEpoxyPopularTelevisionLoading(): List<EpoxyTelevisionData.Shimmer> {
        return mutableListOf(
            EpoxyTelevisionData.Shimmer(epoxyId = 0),
            EpoxyTelevisionData.Shimmer(epoxyId = 1),
            EpoxyTelevisionData.Shimmer(epoxyId = 2),
            EpoxyTelevisionData.Shimmer(epoxyId = 3),
            EpoxyTelevisionData.Shimmer(epoxyId = 4),
            EpoxyTelevisionData.Shimmer(epoxyId = 5),
            EpoxyTelevisionData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyPopularTelevisionError(): List<EpoxyTelevisionData.Error> {
        return mutableListOf(EpoxyTelevisionData.Error)
    }

    override fun setEpoxyTopRatedTelevisionData(televisionData: List<Television>): List<EpoxyTelevisionData.TelevisionData> {
        return epoxyTelevisionMapper.epoxyTopRatedTvListMapper(
            epoxyTelevisionMapper.extractTelevisionToEpoxy(
                televisionData
            )
        )
    }

    override fun setEpoxyTopRatedTelevisionLoading(): List<EpoxyTelevisionData.Shimmer> {
        return mutableListOf(
            EpoxyTelevisionData.Shimmer(epoxyId = 0),
            EpoxyTelevisionData.Shimmer(epoxyId = 1),
            EpoxyTelevisionData.Shimmer(epoxyId = 2),
            EpoxyTelevisionData.Shimmer(epoxyId = 3),
            EpoxyTelevisionData.Shimmer(epoxyId = 4),
            EpoxyTelevisionData.Shimmer(epoxyId = 5),
            EpoxyTelevisionData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyTopRatedTelevisionError(): List<EpoxyTelevisionData.Error> {
        return mutableListOf(EpoxyTelevisionData.Error)
    }
}

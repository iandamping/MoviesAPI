package com.ian.app.muviepedia.core.presentation.model

import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData

fun EpoxyTelevision.toEpoxyPopularData(): EpoxyPopularTelevisionData.TelevisionData {
    return EpoxyPopularTelevisionData.TelevisionData(
        epoxyId = epoxyId,
        originalName = originalName,
        name = name,
        popularity = popularity,
        voteCount = voteCount,
        firstAirDate = firstAirDate,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        id = id,
        voteAverage = voteAverage,
        overview = overview,
        posterPath = posterPath
    )
}

fun Set<EpoxyTelevision>.toListEpoxyPopularTvData(): List<EpoxyPopularTelevisionData.TelevisionData> =
    map {
        it.toEpoxyPopularData()
    }

fun EpoxyTelevision.toEpoxyTopRatedData(): EpoxyTopRatedTelevisionData.TelevisionData {
    return EpoxyTopRatedTelevisionData.TelevisionData(
        epoxyId = epoxyId,
        originalName = originalName,
        name = name,
        popularity = popularity,
        voteCount = voteCount,
        firstAirDate = firstAirDate,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        id = id,
        voteAverage = voteAverage,
        overview = overview,
        posterPath = posterPath
    )
}

fun Set<EpoxyTelevision>.toListEpoxyTopRatedTvData(): List<EpoxyTopRatedTelevisionData.TelevisionData> =
    map {
        it.toEpoxyTopRatedData()
    }

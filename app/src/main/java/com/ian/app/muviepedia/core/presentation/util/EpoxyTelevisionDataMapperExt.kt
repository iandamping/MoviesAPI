package com.ian.app.muviepedia.core.presentation.util

import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevision
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailCompany
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailSimilarContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailVideoData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailVideoData
import com.ian.app.muviepedia.feature.home.epoxy.television.data.EpoxyTelevisionData
import com.ian.app.muviepedia.feature.search.television.EpoxySearchTelevisionData

fun EpoxyTelevision.toTelevisionEpoxyData(): EpoxyTelevisionData.TelevisionData {
    return EpoxyTelevisionData.TelevisionData(
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

fun List<EpoxyTelevision>.toListEpoxyTvData(): List<EpoxyTelevisionData.TelevisionData> =
    map {
        it.toTelevisionEpoxyData()
    }


fun EpoxyTelevision.toEpoxySearchTelevisionData(): EpoxySearchTelevisionData.TelevisionData {
    return EpoxySearchTelevisionData.TelevisionData(
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

fun List<EpoxyTelevision>.toListEpoxySearchTelevisionData(): List<EpoxySearchTelevisionData.TelevisionData> =
    map {
        it.toEpoxySearchTelevisionData()
    }


fun EpoxyTelevisionDetailCompany.toEpoxyDetailCompanyTelevisionData(): EpoxyDetailCompanyData.CompanyData {
    return EpoxyDetailCompanyData.CompanyData(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry,
    )
}

fun List<EpoxyTelevisionDetailCompany>.toListEpoxyDetailCompanyData(): List<EpoxyDetailCompanyData.CompanyData> =
    map { it.toEpoxyDetailCompanyTelevisionData() }


fun EpoxyTelevisionDetailContent.toEpoxyDetailContentTelevisionData(): EpoxyDetailContentData.TelevisionData {
    return EpoxyDetailContentData.TelevisionData(
        epoxyContentId = epoxyContentId,
        title = title,
        tagline = tagline,
        overview = overview,
        voteAverage = voteAverage,
        firstAiringDate = firstAiringDate,
        lastAiringDate = lastAiringDate,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons
    )
}


fun EpoxyTelevisionDetailSimilarContent.toEpoxyDetailSimilarTelevisionData(): EpoxyDetailSimilarData.SimilarData {
    return EpoxyDetailSimilarData.SimilarData(
        epoxyImageId = epoxyId,
        image = posterPath,
        dataId = tvId,
    )
}

fun List<EpoxyTelevisionDetailSimilarContent>.toListEpoxyDetailSimilarData(): List<EpoxyDetailSimilarData.SimilarData> =
    map { it.toEpoxyDetailSimilarTelevisionData() }


fun EpoxyTelevisionDetailVideoData.toTelevisionVideoEpoxyData(): EpoxyDetailVideoData.VideoData {
    return EpoxyDetailVideoData.VideoData(
        id = this.id,
        key = this.key,
    )
}

fun List<EpoxyTelevisionDetailVideoData>.toListTelevisionVideoEpoxyData(): List<EpoxyDetailVideoData.VideoData> =
    map {
        it.toTelevisionVideoEpoxyData()
    }



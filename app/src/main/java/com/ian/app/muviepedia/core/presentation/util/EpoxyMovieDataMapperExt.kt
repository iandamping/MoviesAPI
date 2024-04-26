package com.ian.app.muviepedia.core.presentation.util

import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailCompany
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailSimilarContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailVideoData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailVideoData
import com.ian.app.muviepedia.feature.home.epoxy.data.EpoxyMovieData

fun EpoxyMovie.toEpoxyMovieData(): EpoxyMovieData.MovieData {
    return EpoxyMovieData.MovieData(
        voteCount = voteCount,
        id = id,
        video = video,
        voteAverage = voteAverage,
        title = title,
        popularity = popularity,
        posterPath = posterPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        backdropPath = backdropPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun List<EpoxyMovie>.toListEpoxyMovieData(): List<EpoxyMovieData.MovieData> =
    map { it.toEpoxyMovieData() }


fun EpoxyMovieDetailCompany.toEpoxyDetailCompanyMovieData(): EpoxyDetailCompanyData.CompanyData {
    return EpoxyDetailCompanyData.CompanyData(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry,
    )
}

fun List<EpoxyMovieDetailCompany>.toListEpoxyDetailCompanyData(): List<EpoxyDetailCompanyData.CompanyData> =
    map { it.toEpoxyDetailCompanyMovieData() }

fun EpoxyMovieDetailContent.toEpoxyDetailContentMovieData(): EpoxyDetailContentData.MovieData {
    return EpoxyDetailContentData.MovieData(
        epoxyContentId = epoxyContentId,
        title = title,
        tagline = tagline,
        overview = overview,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        revenue = revenue
    )
}


fun EpoxyMovieDetailSimilarContent.toEpoxyDetailSimilarMovieData(): EpoxyDetailSimilarData.SimilarData {
    return EpoxyDetailSimilarData.SimilarData(
        epoxyImageId = epoxyId,
        image = posterPath,
        dataId = movieId,
    )
}

fun List<EpoxyMovieDetailSimilarContent>.toListEpoxyDetailSimilarData(): List<EpoxyDetailSimilarData.SimilarData> =
    map { it.toEpoxyDetailSimilarMovieData() }


fun EpoxyMovieDetailVideoData.toMovieVideoEpoxyData(): EpoxyDetailVideoData.VideoData {
    return EpoxyDetailVideoData.VideoData(
        id = this.id,
        key = this.key,
    )
}

fun List<EpoxyMovieDetailVideoData>.toListMovieVideoEpoxyData(): List<EpoxyDetailVideoData.VideoData> =
    map {
        it.toMovieVideoEpoxyData()
    }



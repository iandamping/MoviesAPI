package com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.television

import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.data.repository.model.Video
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevision
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailCompany
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailSimilarContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailVideoData
import com.ian.app.muviepedia.core.presentation.util.toEpoxyDetailContentTelevisionData
import com.ian.app.muviepedia.core.presentation.util.toListEpoxyDetailCompanyData
import com.ian.app.muviepedia.core.presentation.util.toListEpoxyDetailSimilarData
import com.ian.app.muviepedia.core.presentation.util.toListEpoxySearchTelevisionData
import com.ian.app.muviepedia.core.presentation.util.toListEpoxyTvData
import com.ian.app.muviepedia.core.presentation.util.toListTelevisionVideoEpoxyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailVideoData
import com.ian.app.muviepedia.feature.home.epoxy.television.data.EpoxyTelevisionData
import com.ian.app.muviepedia.feature.search.television.EpoxySearchTelevisionData
import javax.inject.Inject
import kotlin.random.Random

class EpoxyTelevisionMapperImpl @Inject constructor() : EpoxyTelevisionMapper {

    override fun extractTelevisionToEpoxy(televisionData: List<Television>): List<EpoxyTelevision> {
        val result = televisionData.map {
            EpoxyTelevision(
                originalName = it.originalName,
                name = it.name,
                popularity = it.popularity,
                voteCount = it.voteCount,
                firstAirDate = it.firstAirDate,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
                id = it.id,
                voteAverage = it.voteAverage,
                overview = it.overview,
                posterPath = it.posterPath
            )
        }

        return result
    }

    override fun extractDetailCompanyTelevisionToEpoxy(televisionData: List<TelevisionDetail.ProductionCompany>): List<EpoxyTelevisionDetailCompany> {
        val result = televisionData.map {
            EpoxyTelevisionDetailCompany(
                id = it.id,
                logoPath = it.logoPath,
                name = it.name,
                originCountry = it.originCountry,
            )
        }

        return result
    }

    override fun extractDetailVideoTelevisionToEpoxy(televisionData: List<Video.ItemVideoData>): List<EpoxyTelevisionDetailVideoData> {
        val result = televisionData.map {
            EpoxyTelevisionDetailVideoData(
                id = it.id,
                site = it.site,
                key = it.key,
                type = it.type
            )
        }

        return result
    }

    override fun extractDetailContentTelevisionToEpoxy(televisionData: TelevisionDetail): EpoxyTelevisionDetailContent {
        return EpoxyTelevisionDetailContent(
            epoxyImageId = Random.nextInt(500, 600),
            epoxyContentId = Random.nextInt(700, 800),
            backdropPath = televisionData.backdropPath,
            title = televisionData.title,
            tagline = televisionData.tagline,
            overview = televisionData.overview,
            voteAverage = televisionData.voteAverage,
            firstAiringDate = televisionData.firstAiringDate,
            lastAiringDate = televisionData.lastAiringDate,
            numberOfEpisodes = televisionData.numberOfEpisodes.toString(),
            numberOfSeasons = televisionData.numberOfSeasons.toString()
        )
    }

    override fun extractDetailSimilarTelevisionToEpoxy(televisionData: List<Television>): List<EpoxyTelevisionDetailSimilarContent> {
        val result = televisionData.map {
            EpoxyTelevisionDetailSimilarContent(
                epoxyId = Random.nextInt(900, 1000),
                posterPath = it.posterPath,
                tvId = it.id
            )
        }
        return result
    }

    override fun epoxySearchTelevisionListMapper(data: List<EpoxyTelevision>): List<EpoxySearchTelevisionData.TelevisionData> {
        return data.toListEpoxySearchTelevisionData()
    }

    override fun epoxyPopularTVListMapper(data: List<EpoxyTelevision>): List<EpoxyTelevisionData.TelevisionData> {
        return data.toListEpoxyTvData()
    }

    override fun epoxyTopRatedTvListMapper(data: List<EpoxyTelevision>): List<EpoxyTelevisionData.TelevisionData> {
        return data.toListEpoxyTvData()
    }

    override fun epoxyDetailCompanyTelevisionListMapper(data: List<EpoxyTelevisionDetailCompany>): List<EpoxyDetailCompanyData.CompanyData> {
        return data.toListEpoxyDetailCompanyData()
    }

    override fun epoxyDetailContentTelevisionMapper(data: EpoxyTelevisionDetailContent): EpoxyDetailContentData.TelevisionData {
        return data.toEpoxyDetailContentTelevisionData()
    }

    override fun epoxyDetailSimilarTelevisionListMapper(data: List<EpoxyTelevisionDetailSimilarContent>): List<EpoxyDetailSimilarData.SimilarData> {
        return data.toListEpoxyDetailSimilarData()
    }

    override fun epoxyDetailVideoTelevisionListMapper(data: List<EpoxyTelevisionDetailVideoData>): List<EpoxyDetailVideoData.VideoData> {
        return data.toListTelevisionVideoEpoxyData()
    }
}

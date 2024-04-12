package com.ian.app.muviepedia.core.presentation.epoxyMapper.detail

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.movie.EpoxyMovieMapper
import com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.television.EpoxyTelevisionMapper
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailImageData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData
import java.util.UUID
import javax.inject.Inject
import kotlin.random.Random

class EpoxyDetailScreenSetterImpl @Inject constructor(
    private val epoxyMovieMapper: EpoxyMovieMapper,
    private val epoxyTelevisionMapper: EpoxyTelevisionMapper
) :
    EpoxyDetailScreenSetter {

    override fun setEpoxyDetailMovieCompanyData(movieData: List<MovieDetail.ProductionCompany>): List<EpoxyDetailCompanyData.CompanyData> {
        return epoxyMovieMapper.epoxyDetailCompanyMovieListMapper(
            epoxyMovieMapper.extractDetailCompanyMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyDetailTelevisionCompanyData(televisionData: List<TelevisionDetail.ProductionCompany>): List<EpoxyDetailCompanyData.CompanyData> {
        return epoxyTelevisionMapper.epoxyDetailCompanyTelevisionListMapper(
            epoxyTelevisionMapper.extractDetailCompanyTelevisionToEpoxy(
                televisionData
            )
        )
    }

    override fun setEpoxyDetailCompanyLoading(): List<EpoxyDetailCompanyData.Shimmer> {
        return mutableListOf(
            EpoxyDetailCompanyData.Shimmer(epoxyId = 0),
            EpoxyDetailCompanyData.Shimmer(epoxyId = 1),
            EpoxyDetailCompanyData.Shimmer(epoxyId = 2),
            EpoxyDetailCompanyData.Shimmer(epoxyId = 3),
        )
    }

    override fun setEpoxyDetailCompanyError(errorMessage: String): List<EpoxyDetailCompanyData.Error> {
        return mutableListOf(EpoxyDetailCompanyData.Error(errorMessage))
    }

    override fun setEpoxyDetailMovieContentData(movieData: MovieDetail): EpoxyDetailContentData.MovieData {
        return epoxyMovieMapper.epoxyDetailContentMovieMapper(
            epoxyMovieMapper.extractDetailContentMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyDetailTelevisionContentData(televisionData: TelevisionDetail): EpoxyDetailContentData {
        return epoxyTelevisionMapper.epoxyDetailContentTelevisionMapper(
            epoxyTelevisionMapper.extractDetailContentTelevisionToEpoxy(
                televisionData
            )
        )
    }

    override fun setEpoxyDetailContentLoading(): EpoxyDetailContentData.Shimmer {
        return EpoxyDetailContentData.Shimmer(epoxyId = Random.nextInt(800, 810))
    }

    override fun setEpoxyDetailContentError(errorMessage: String): EpoxyDetailContentData.Error {
        return EpoxyDetailContentData.Error(errorMessage)
    }

    override fun setEpoxyDetailImageContentData(image: String): EpoxyDetailImageData {
        return EpoxyDetailImageData.ImageData(
            epoxyImageId = UUID.randomUUID().toString(),
            image = image
        )
    }

    override fun setEpoxyDetailImageLoading(): EpoxyDetailImageData.Shimmer {
        return EpoxyDetailImageData.Shimmer(epoxyId = UUID.randomUUID().toString())
    }

    override fun setEpoxyDetailImageError(): EpoxyDetailImageData.Error {
        return EpoxyDetailImageData.Error
    }

    override fun setEpoxyDetailTelevisionSimilarData(televisionData: List<Television>): List<EpoxyDetailSimilarData.SimilarData> {
        return epoxyTelevisionMapper.epoxyDetailSimilarTelevisionListMapper(
            epoxyTelevisionMapper.extractDetailSimilarTelevisionToEpoxy(
                televisionData
            )
        )
    }

    override fun setEpoxyDetailMovieSimilarData(movieData: List<Movie>): List<EpoxyDetailSimilarData.SimilarData> {
        return epoxyMovieMapper.epoxyDetailSimilarMovieListMapper(
            epoxyMovieMapper.extractDetailSimilarMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyDetailSimilarLoading(): List<EpoxyDetailSimilarData.Shimmer> {
        return mutableListOf(
            EpoxyDetailSimilarData.Shimmer(epoxyId = 0),
            EpoxyDetailSimilarData.Shimmer(epoxyId = 1),
            EpoxyDetailSimilarData.Shimmer(epoxyId = 2),
            EpoxyDetailSimilarData.Shimmer(epoxyId = 3),
        )
    }

    override fun setEpoxyDetailSimilarError(): List<EpoxyDetailSimilarData.Error> {
        return mutableListOf(EpoxyDetailSimilarData.Error)
    }
}
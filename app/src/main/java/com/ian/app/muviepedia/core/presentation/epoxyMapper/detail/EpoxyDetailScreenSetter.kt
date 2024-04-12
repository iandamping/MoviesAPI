package com.ian.app.muviepedia.core.presentation.epoxyMapper.detail

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailImageData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData

interface EpoxyDetailScreenSetter {

    fun setEpoxyDetailMovieCompanyData(movieData: List<MovieDetail.ProductionCompany>): List<EpoxyDetailCompanyData.CompanyData>

    fun setEpoxyDetailTelevisionCompanyData(televisionData: List<TelevisionDetail.ProductionCompany>): List<EpoxyDetailCompanyData.CompanyData>

    fun setEpoxyDetailCompanyLoading(): List<EpoxyDetailCompanyData.Shimmer>

    fun setEpoxyDetailCompanyError(errorMessage: String): List<EpoxyDetailCompanyData.Error>

    fun setEpoxyDetailMovieContentData(movieData: MovieDetail): EpoxyDetailContentData.MovieData

    fun setEpoxyDetailTelevisionContentData(televisionData: TelevisionDetail): EpoxyDetailContentData

    fun setEpoxyDetailContentLoading(): EpoxyDetailContentData.Shimmer

    fun setEpoxyDetailContentError(errorMessage: String): EpoxyDetailContentData.Error

    fun setEpoxyDetailImageContentData(image: String): EpoxyDetailImageData

    fun setEpoxyDetailImageLoading(): EpoxyDetailImageData.Shimmer

    fun setEpoxyDetailImageError(): EpoxyDetailImageData.Error

    fun setEpoxyDetailTelevisionSimilarData(televisionData: List<Television>): List<EpoxyDetailSimilarData.SimilarData>

    fun setEpoxyDetailMovieSimilarData(movieData: List<Movie>): List<EpoxyDetailSimilarData.SimilarData>

    fun setEpoxyDetailSimilarLoading(): List<EpoxyDetailSimilarData.Shimmer>

    fun setEpoxyDetailSimilarError(): List<EpoxyDetailSimilarData.Error>

}
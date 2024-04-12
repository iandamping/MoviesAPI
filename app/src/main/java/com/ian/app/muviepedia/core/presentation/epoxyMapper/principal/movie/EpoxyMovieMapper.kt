package com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.movie

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailCompany
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailSimilarContent
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData
import com.ian.app.muviepedia.feature.home.epoxy.data.EpoxyMovieData

interface EpoxyMovieMapper {

    fun extractMovieToEpoxy(movieData: List<Movie>): List<EpoxyMovie>

    fun extractDetailCompanyMovieToEpoxy(movieData: List<MovieDetail.ProductionCompany>): List<EpoxyMovieDetailCompany>

    fun extractDetailContentMovieToEpoxy(movieData: MovieDetail): EpoxyMovieDetailContent

    fun extractDetailSimilarMovieToEpoxy(movieData: List<Movie>): List<EpoxyMovieDetailSimilarContent>

    fun epoxyPopularMovieListMapper(data: List<EpoxyMovie>): List<EpoxyMovieData.MovieData>

    fun epoxyNowPlayingMovieListMapper(data: List<EpoxyMovie>): List<EpoxyMovieData.MovieData>

    fun epoxyTopRatedMovieListMapper(data: List<EpoxyMovie>): List<EpoxyMovieData.MovieData>

    fun epoxyUpComingMovieListMapper(data: List<EpoxyMovie>): List<EpoxyMovieData.MovieData>

    fun epoxyDetailCompanyMovieListMapper(data: List<EpoxyMovieDetailCompany>): List<EpoxyDetailCompanyData.CompanyData>

    fun epoxyDetailContentMovieMapper(data: EpoxyMovieDetailContent): EpoxyDetailContentData.MovieData

    fun epoxyDetailSimilarMovieListMapper(data: List<EpoxyMovieDetailSimilarContent>): List<EpoxyDetailSimilarData.SimilarData>
}

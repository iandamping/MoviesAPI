package com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.movie

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailCompany
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovieDetailSimilarContent
import com.ian.app.muviepedia.core.presentation.util.toEpoxyDetailContentMovieData
import com.ian.app.muviepedia.core.presentation.util.toListEpoxyDetailCompanyData
import com.ian.app.muviepedia.core.presentation.util.toListEpoxyDetailSimilarData
import com.ian.app.muviepedia.core.presentation.util.toListEpoxyMovieData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData
import com.ian.app.muviepedia.feature.home.epoxy.data.EpoxyMovieData
import javax.inject.Inject
import kotlin.random.Random

class EpoxyMovieMapperImpl @Inject constructor() :
    EpoxyMovieMapper {

    override fun extractMovieToEpoxy(movieData: List<Movie>): List<EpoxyMovie> {
        val result = movieData.map {
            EpoxyMovie(
                voteCount = it.voteCount,
                id = it.id,
                video = it.video,
                voteAverage = it.voteAverage,
                title = it.title,
                popularity = it.popularity,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                backdropPath = it.backdropPath,
                adult = it.adult,
                overview = it.overview,
                releaseDate = it.releaseDate
            )
        }

        return result
    }

    override fun extractDetailCompanyMovieToEpoxy(movieData: List<MovieDetail.ProductionCompany>): List<EpoxyMovieDetailCompany> {
        val result = movieData.map {
            EpoxyMovieDetailCompany(
                id = it.id,
                logoPath = it.logoPath,
                name = it.name,
                originCountry = it.originCountry,
            )
        }

        return result
    }

    override fun extractDetailContentMovieToEpoxy(movieData: MovieDetail): EpoxyMovieDetailContent {
        return EpoxyMovieDetailContent(
            epoxyImageId = Random.nextInt(100, 200),
            epoxyContentId = Random.nextInt(300, 400),
            backdropPath = movieData.backdropPath,
            title = movieData.title,
            tagline = movieData.tagline,
            overview = movieData.overview,
            voteAverage = movieData.voteAverage,
            releaseDate = movieData.releaseDate,
            revenue = movieData.revenue
        )
    }

    override fun extractDetailSimilarMovieToEpoxy(movieData: List<Movie>): List<EpoxyMovieDetailSimilarContent> {
        val result = movieData.map {
            EpoxyMovieDetailSimilarContent(
                epoxyId = Random.nextInt(900, 1000),
                posterPath = it.posterPath,
                movieId = it.id
            )
        }
        return result
    }


    override fun epoxyPopularMovieListMapper(data: List<EpoxyMovie>): List<EpoxyMovieData.MovieData> {
        return data.toListEpoxyMovieData()
    }

    override fun epoxyNowPlayingMovieListMapper(data: List<EpoxyMovie>): List<EpoxyMovieData.MovieData> {
        return data.toListEpoxyMovieData()
    }

    override fun epoxyTopRatedMovieListMapper(data: List<EpoxyMovie>): List<EpoxyMovieData.MovieData> {
        return data.toListEpoxyMovieData()
    }

    override fun epoxyUpComingMovieListMapper(data: List<EpoxyMovie>): List<EpoxyMovieData.MovieData> {
        return data.toListEpoxyMovieData()
    }

    override fun epoxyDetailCompanyMovieListMapper(data: List<EpoxyMovieDetailCompany>): List<EpoxyDetailCompanyData.CompanyData> {
        return data.toListEpoxyDetailCompanyData()
    }

    override fun epoxyDetailContentMovieMapper(data: EpoxyMovieDetailContent): EpoxyDetailContentData.MovieData {
        return data.toEpoxyDetailContentMovieData()
    }

    override fun epoxyDetailSimilarMovieListMapper(data: List<EpoxyMovieDetailSimilarContent>): List<EpoxyDetailSimilarData.SimilarData> {
        return data.toListEpoxyDetailSimilarData()
    }
}

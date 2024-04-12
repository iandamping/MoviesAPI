package com.ian.app.muviepedia.core.presentation.epoxyMapper.home.movie

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.movie.EpoxyMovieMapper
import com.ian.app.muviepedia.feature.home.epoxy.data.EpoxyMovieData
import javax.inject.Inject

class EpoxyHomeMovieScreenSetterImpl @Inject constructor(private val epoxyMovieMapper: EpoxyMovieMapper) :
    EpoxyHomeMovieScreenSetter {

    override fun setEpoxyPopularMovieData(
        movieData: List<Movie>,
    ): List<EpoxyMovieData.MovieData> {
        return epoxyMovieMapper.epoxyPopularMovieListMapper(
            epoxyMovieMapper.extractMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyPopularMovieLoading(): List<EpoxyMovieData.Shimmer> {
        return mutableListOf(
            EpoxyMovieData.Shimmer(epoxyId = 0),
            EpoxyMovieData.Shimmer(epoxyId = 1),
            EpoxyMovieData.Shimmer(epoxyId = 2),
            EpoxyMovieData.Shimmer(epoxyId = 3),
            EpoxyMovieData.Shimmer(epoxyId = 4),
            EpoxyMovieData.Shimmer(epoxyId = 5),
            EpoxyMovieData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyPopularMovieError(): List<EpoxyMovieData.Error> {
        return mutableListOf(EpoxyMovieData.Error)
    }

    override fun setEpoxyNowPlayingMovieData(movieData: List<Movie>): List<EpoxyMovieData.MovieData> {
        return epoxyMovieMapper.epoxyNowPlayingMovieListMapper(
            epoxyMovieMapper.extractMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyNowPlayingMovieLoading(): List<EpoxyMovieData.Shimmer> {
        return mutableListOf(
            EpoxyMovieData.Shimmer(epoxyId = 0),
            EpoxyMovieData.Shimmer(epoxyId = 1),
            EpoxyMovieData.Shimmer(epoxyId = 2),
            EpoxyMovieData.Shimmer(epoxyId = 3),
            EpoxyMovieData.Shimmer(epoxyId = 4),
            EpoxyMovieData.Shimmer(epoxyId = 5),
            EpoxyMovieData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyNowPlayingMovieError(): List<EpoxyMovieData.Error> {
        return mutableListOf(EpoxyMovieData.Error)
    }

    override fun setEpoxyTopRatedMovieData(movieData: List<Movie>): List<EpoxyMovieData.MovieData> {
        return epoxyMovieMapper.epoxyTopRatedMovieListMapper(
            epoxyMovieMapper.extractMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyTopRatedMovieLoading(): List<EpoxyMovieData.Shimmer> {
        return mutableListOf(
            EpoxyMovieData.Shimmer(epoxyId = 0),
            EpoxyMovieData.Shimmer(epoxyId = 1),
            EpoxyMovieData.Shimmer(epoxyId = 2),
            EpoxyMovieData.Shimmer(epoxyId = 3),
            EpoxyMovieData.Shimmer(epoxyId = 4),
            EpoxyMovieData.Shimmer(epoxyId = 5),
            EpoxyMovieData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyTopRatedMovieError(): List<EpoxyMovieData.Error> {
        return mutableListOf(EpoxyMovieData.Error)
    }

    override fun setEpoxyUpComingMovieData(movieData: List<Movie>): List<EpoxyMovieData.MovieData> {
        return epoxyMovieMapper.epoxyUpComingMovieListMapper(
            epoxyMovieMapper.extractMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyUpComingMovieLoading(): List<EpoxyMovieData.Shimmer> {
        return mutableListOf(
            EpoxyMovieData.Shimmer(epoxyId = 0),
            EpoxyMovieData.Shimmer(epoxyId = 1),
            EpoxyMovieData.Shimmer(epoxyId = 2),
            EpoxyMovieData.Shimmer(epoxyId = 3),
            EpoxyMovieData.Shimmer(epoxyId = 4),
            EpoxyMovieData.Shimmer(epoxyId = 5),
            EpoxyMovieData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyUpComingMovieError(): List<EpoxyMovieData.Error> {
        return mutableListOf(EpoxyMovieData.Error)
    }
}

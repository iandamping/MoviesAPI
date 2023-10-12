package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import javax.inject.Inject

class EpoxyHomeMovieSetterImpl @Inject constructor(private val epoxyMapper: EpoxyMapper) :
    EpoxyHomeMovieSetter {

    override fun setEpoxyPopularMovieData(
        movieData: List<Movie>,
    ): List<EpoxyPopularMovieData.MovieData> {
        return epoxyMapper.epoxyPopularMovieListMapper(
            epoxyMapper.extractMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyPopularMovieLoading(): List<EpoxyPopularMovieData.Shimmer> {
        return mutableListOf(
            EpoxyPopularMovieData.Shimmer(epoxyId = 0),
            EpoxyPopularMovieData.Shimmer(epoxyId = 1),
            EpoxyPopularMovieData.Shimmer(epoxyId = 2),
            EpoxyPopularMovieData.Shimmer(epoxyId = 3),
            EpoxyPopularMovieData.Shimmer(epoxyId = 4),
            EpoxyPopularMovieData.Shimmer(epoxyId = 5),
            EpoxyPopularMovieData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyPopularMovieError(): List<EpoxyPopularMovieData.Error> {
        return mutableListOf(EpoxyPopularMovieData.Error)
    }

    override fun setEpoxyNowPlayingMovieData(movieData: List<Movie>): List<EpoxyNowPlayingMovieData.MovieData> {
        return epoxyMapper.epoxyNowPlayingMovieListMapper(
            epoxyMapper.extractMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyNowPlayingMovieLoading(): List<EpoxyNowPlayingMovieData.Shimmer> {
        return mutableListOf(
            EpoxyNowPlayingMovieData.Shimmer(epoxyId = 0),
            EpoxyNowPlayingMovieData.Shimmer(epoxyId = 1),
            EpoxyNowPlayingMovieData.Shimmer(epoxyId = 2),
            EpoxyNowPlayingMovieData.Shimmer(epoxyId = 3),
            EpoxyNowPlayingMovieData.Shimmer(epoxyId = 4),
            EpoxyNowPlayingMovieData.Shimmer(epoxyId = 5),
            EpoxyNowPlayingMovieData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyNowPlayingMovieError(): List<EpoxyNowPlayingMovieData.Error> {
        return mutableListOf(EpoxyNowPlayingMovieData.Error)
    }

    override fun setEpoxyTopRatedMovieData(movieData: List<Movie>): List<EpoxyTopRatedMovieData.MovieData> {
        return epoxyMapper.epoxyTopRatedMovieListMapper(
            epoxyMapper.extractMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyTopRatedMovieLoading(): List<EpoxyTopRatedMovieData.Shimmer> {
        return mutableListOf(
            EpoxyTopRatedMovieData.Shimmer(epoxyId = 0),
            EpoxyTopRatedMovieData.Shimmer(epoxyId = 1),
            EpoxyTopRatedMovieData.Shimmer(epoxyId = 2),
            EpoxyTopRatedMovieData.Shimmer(epoxyId = 3),
            EpoxyTopRatedMovieData.Shimmer(epoxyId = 4),
            EpoxyTopRatedMovieData.Shimmer(epoxyId = 5),
            EpoxyTopRatedMovieData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyTopRatedMovieError(): List<EpoxyTopRatedMovieData.Error> {
        return mutableListOf(EpoxyTopRatedMovieData.Error)
    }

    override fun setEpoxyUpComingMovieData(movieData: List<Movie>): List<EpoxyUpComingMovieData.MovieData> {
        return epoxyMapper.epoxyUpComingMovieListMapper(
            epoxyMapper.extractMovieToEpoxy(
                movieData
            )
        )
    }

    override fun setEpoxyUpComingMovieLoading(): List<EpoxyUpComingMovieData.Shimmer> {
        return mutableListOf(
            EpoxyUpComingMovieData.Shimmer(epoxyId = 0),
            EpoxyUpComingMovieData.Shimmer(epoxyId = 1),
            EpoxyUpComingMovieData.Shimmer(epoxyId = 2),
            EpoxyUpComingMovieData.Shimmer(epoxyId = 3),
            EpoxyUpComingMovieData.Shimmer(epoxyId = 4),
            EpoxyUpComingMovieData.Shimmer(epoxyId = 5),
            EpoxyUpComingMovieData.Shimmer(epoxyId = 6),
        )
    }

    override fun setEpoxyUpComingMovieError(): List<EpoxyUpComingMovieData.Error> {
        return mutableListOf(EpoxyUpComingMovieData.Error)
    }
}

package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData

interface EpoxyHomeMovieSetter {

    fun setEpoxyPopularMovieData(movieData: List<Movie>): List<EpoxyPopularMovieData.MovieData>

    fun setEpoxyPopularMovieLoading(): List<EpoxyPopularMovieData.Shimmer>

    fun setEpoxyPopularMovieError(): List<EpoxyPopularMovieData.Error>

    fun setEpoxyNowPlayingMovieData(movieData: List<Movie>): List<EpoxyNowPlayingMovieData.MovieData>

    fun setEpoxyNowPlayingMovieLoading(): List<EpoxyNowPlayingMovieData.Shimmer>

    fun setEpoxyNowPlayingMovieError(): List<EpoxyNowPlayingMovieData.Error>

    fun setEpoxyTopRatedMovieData(movieData: List<Movie>): List<EpoxyTopRatedMovieData.MovieData>

    fun setEpoxyTopRatedMovieLoading(): List<EpoxyTopRatedMovieData.Shimmer>

    fun setEpoxyTopRatedMovieError(): List<EpoxyTopRatedMovieData.Error>

    fun setEpoxyUpComingMovieData(movieData: List<Movie>): List<EpoxyUpComingMovieData.MovieData>

    fun setEpoxyUpComingMovieLoading(): List<EpoxyUpComingMovieData.Shimmer>

    fun setEpoxyUpComingMovieError(): List<EpoxyUpComingMovieData.Error>
}

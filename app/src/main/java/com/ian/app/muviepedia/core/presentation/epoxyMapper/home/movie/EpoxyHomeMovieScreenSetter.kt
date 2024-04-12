package com.ian.app.muviepedia.core.presentation.epoxyMapper.home.movie

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.feature.home.epoxy.data.EpoxyMovieData

interface EpoxyHomeMovieScreenSetter {

    fun setEpoxyPopularMovieData(movieData: List<Movie>): List<EpoxyMovieData.MovieData>

    fun setEpoxyPopularMovieLoading(): List<EpoxyMovieData.Shimmer>

    fun setEpoxyPopularMovieError(): List<EpoxyMovieData.Error>

    fun setEpoxyNowPlayingMovieData(movieData: List<Movie>): List<EpoxyMovieData.MovieData>

    fun setEpoxyNowPlayingMovieLoading(): List<EpoxyMovieData.Shimmer>

    fun setEpoxyNowPlayingMovieError(): List<EpoxyMovieData.Error>

    fun setEpoxyTopRatedMovieData(movieData: List<Movie>): List<EpoxyMovieData.MovieData>

    fun setEpoxyTopRatedMovieLoading(): List<EpoxyMovieData.Shimmer>

    fun setEpoxyTopRatedMovieError(): List<EpoxyMovieData.Error>

    fun setEpoxyUpComingMovieData(movieData: List<Movie>): List<EpoxyMovieData.MovieData>

    fun setEpoxyUpComingMovieLoading(): List<EpoxyMovieData.Shimmer>

    fun setEpoxyUpComingMovieError(): List<EpoxyMovieData.Error>
}

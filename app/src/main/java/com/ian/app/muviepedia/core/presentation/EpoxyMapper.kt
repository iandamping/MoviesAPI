package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxyUpComingMovieData

interface EpoxyMapper {

    fun extractMovieToEpoxy(movieData: List<Movie>): Set<EpoxyMovie>

    fun epoxyPopularMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyPopularMovieData.MovieData>

    fun epoxyNowPlayingMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyNowPlayingMovieData.MovieData>

    fun epoxyTopRatedMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyTopRatedMovieData.MovieData>

    fun epoxyUpComingMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyUpComingMovieData.MovieData>
}
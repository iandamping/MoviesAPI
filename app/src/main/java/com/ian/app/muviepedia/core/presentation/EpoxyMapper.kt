package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData

interface EpoxyMapper {

    fun extractMovieToEpoxy(movieData: List<Movie>): Set<EpoxyMovie>

    fun epoxyPopularMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyPopularMovieData.MovieData>

    fun epoxyNowPlayingMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyNowPlayingMovieData.MovieData>
}
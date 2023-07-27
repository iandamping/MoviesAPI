package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.feature.home.epoxy.EpoxyPopularMovieData

interface EpoxyMapper {

    suspend fun extractMovieToEpoxy(movieData: List<Movie>): Set<EpoxyMovie>

    fun epoxyPopularMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyPopularMovieData.MovieData>
}
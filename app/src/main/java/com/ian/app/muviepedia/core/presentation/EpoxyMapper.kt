package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevision
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData
import com.ian.app.muviepedia.feature.search.epoxy.EpoxySearchMovieData

interface EpoxyMapper {

    fun extractMovieToEpoxy(movieData: List<Movie>): Set<EpoxyMovie>

    fun epoxyPopularMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyPopularMovieData.MovieData>

    fun epoxyNowPlayingMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyNowPlayingMovieData.MovieData>

    fun epoxyTopRatedMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyTopRatedMovieData.MovieData>

    fun epoxyUpComingMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyUpComingMovieData.MovieData>

    fun epoxySearchMovieListMapper(data: Set<EpoxyMovie>): List<EpoxySearchMovieData.MovieData>

    fun extractTelevisionToEpoxy(televisionData: List<Television>): Set<EpoxyTelevision>

    fun epoxyPopularTelevisionListMapper(data: Set<EpoxyTelevision>): List<EpoxyPopularTelevisionData.TelevisionData>

    fun epoxyTopRatedTelevisionListMapper(data: Set<EpoxyTelevision>): List<EpoxyTopRatedTelevisionData.TelevisionData>
}

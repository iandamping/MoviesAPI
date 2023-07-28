package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
import javax.inject.Inject

class EpoxyMapperImpl @Inject constructor() :
    EpoxyMapper {

    override fun extractMovieToEpoxy(movieData: List<Movie>): Set<EpoxyMovie> {
        val listOfId = List(movieData.size) { it + 1 }
        val result = movieData.zip(listOfId).map {
            EpoxyMovie(
                epoxyId = it.second,
                vote_count = it.first.vote_count,
                id = it.first.id,
                video = it.first.video,
                vote_average = it.first.vote_average,
                title = it.first.title,
                popularity = it.first.popularity,
                poster_path = it.first.poster_path,
                original_language = it.first.original_language,
                original_title = it.first.original_title,
                backdrop_path = it.first.backdrop_path,
                adult = it.first.adult,
                overview = it.first.overview,
                release_date = it.first.release_date
            )
        }.toSet()

        return result
    }

    override fun epoxyPopularMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyPopularMovieData.MovieData> {
        return data.map { it.toEpoxyPopularData() }
    }

    override fun epoxyNowPlayingMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyNowPlayingMovieData.MovieData> {
        return data.map { it.toEpoxyNowPlayingData() }
    }

    private fun EpoxyMovie.toEpoxyPopularData(): EpoxyPopularMovieData.MovieData {
        return EpoxyPopularMovieData.MovieData(
            epoxyId = epoxyId,
            vote_count = vote_count,
            id = id,
            video = video,
            vote_average = vote_average,
            title = title,
            popularity = popularity,
            poster_path = poster_path,
            original_language = original_language,
            original_title = original_title,
            backdrop_path = backdrop_path,
            adult = adult,
            overview = overview,
            release_date = release_date
        )
    }
    private fun EpoxyMovie.toEpoxyNowPlayingData(): EpoxyNowPlayingMovieData.MovieData {
        return EpoxyNowPlayingMovieData.MovieData(
            epoxyId = epoxyId,
            vote_count = vote_count,
            id = id,
            video = video,
            vote_average = vote_average,
            title = title,
            popularity = popularity,
            poster_path = poster_path,
            original_language = original_language,
            original_title = original_title,
            backdrop_path = backdrop_path,
            adult = adult,
            overview = overview,
            release_date = release_date
        )
    }
}
package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevision
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyNowPlayingData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyPopularData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyPopularTvData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxySearchMovieData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxySearchTelevisionData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyTopRatedData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyTopRatedTvData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyUpComingData
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData
import com.ian.app.muviepedia.feature.search.epoxy.EpoxySearchMovieData
import com.ian.app.muviepedia.feature.search.television.EpoxySearchTelevisionData
import javax.inject.Inject

class EpoxyMapperImpl @Inject constructor() :
    EpoxyMapper {

    override fun extractMovieToEpoxy(movieData: List<Movie>): Set<EpoxyMovie> {
        val listOfId = List(movieData.size) { it + 1 }
        val result = movieData.zip(listOfId).map {
            EpoxyMovie(
                epoxyId = it.second,
                voteCount = it.first.voteCount,
                id = it.first.id,
                video = it.first.video,
                voteAverage = it.first.voteAverage,
                title = it.first.title,
                popularity = it.first.popularity,
                posterPath = it.first.posterPath,
                originalLanguage = it.first.originalLanguage,
                originalTitle = it.first.originalTitle,
                backdropPath = it.first.backdropPath,
                adult = it.first.adult,
                overview = it.first.overview,
                releaseDate = it.first.releaseDate
            )
        }.toSet()

        return result
    }

    override fun epoxyPopularMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyPopularMovieData.MovieData> {
        return data.toListEpoxyPopularData()
    }

    override fun epoxyNowPlayingMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyNowPlayingMovieData.MovieData> {
        return data.toListEpoxyNowPlayingData()
    }

    override fun epoxyTopRatedMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyTopRatedMovieData.MovieData> {
        return data.toListEpoxyTopRatedData()
    }

    override fun epoxyUpComingMovieListMapper(data: Set<EpoxyMovie>): List<EpoxyUpComingMovieData.MovieData> {
        return data.toListEpoxyUpComingData()
    }

    override fun epoxySearchMovieListMapper(data: Set<EpoxyMovie>): List<EpoxySearchMovieData.MovieData> {
        return data.toListEpoxySearchMovieData()
    }

    override fun epoxySearchTelevisionListMapper(data: Set<EpoxyTelevision>): List<EpoxySearchTelevisionData.TelevisionData> {
        return data.toListEpoxySearchTelevisionData()
    }

    override fun extractTelevisionToEpoxy(televisionData: List<Television>): Set<EpoxyTelevision> {
        val listOfId = List(televisionData.size) { it + 1 }
        val result = televisionData.zip(listOfId).map {
            EpoxyTelevision(
                epoxyId = it.second,
                originalName = it.first.originalName,
                name = it.first.name,
                popularity = it.first.popularity,
                voteCount = it.first.voteCount,
                firstAirDate = it.first.firstAirDate,
                backdropPath = it.first.backdropPath,
                originalLanguage = it.first.originalLanguage,
                id = it.first.id,
                voteAverage = it.first.voteAverage,
                overview = it.first.overview,
                posterPath = it.first.posterPath
            )
        }.toSet()

        return result
    }

    override fun epoxyPopularTVListMapper(data: Set<EpoxyTelevision>): List<EpoxyPopularTelevisionData.TelevisionData> {
        return data.toListEpoxyPopularTvData()
    }

    override fun epoxyTopRatedTvListMapper(data: Set<EpoxyTelevision>): List<EpoxyTopRatedTelevisionData.TelevisionData> {
        return data.toListEpoxyTopRatedTvData()
    }
}

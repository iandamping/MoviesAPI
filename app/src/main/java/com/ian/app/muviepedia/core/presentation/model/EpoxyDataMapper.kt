package com.ian.app.muviepedia.core.presentation.model

import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData
import com.ian.app.muviepedia.feature.search.epoxy.EpoxySearchMovieData

fun EpoxyTelevision.toEpoxyPopularData(): EpoxyPopularTelevisionData.TelevisionData {
    return EpoxyPopularTelevisionData.TelevisionData(
        epoxyId = epoxyId,
        originalName = originalName,
        name = name,
        popularity = popularity,
        voteCount = voteCount,
        firstAirDate = firstAirDate,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        id = id,
        voteAverage = voteAverage,
        overview = overview,
        posterPath = posterPath
    )
}

fun Set<EpoxyTelevision>.toListEpoxyPopularTvData():List<EpoxyPopularTelevisionData.TelevisionData> = map { it.toEpoxyPopularData() }


fun EpoxyTelevision.toEpoxyTopRatedData(): EpoxyTopRatedTelevisionData.TelevisionData {
    return EpoxyTopRatedTelevisionData.TelevisionData(
        epoxyId = epoxyId,
        originalName = originalName,
        name = name,
        popularity = popularity,
        voteCount = voteCount,
        firstAirDate = firstAirDate,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        id = id,
        voteAverage = voteAverage,
        overview = overview,
        posterPath = posterPath
    )
}

fun Set<EpoxyTelevision>.toListEpoxyTopRatedTvData():List<EpoxyTopRatedTelevisionData.TelevisionData> = map { it.toEpoxyTopRatedData() }



fun EpoxyMovie.toEpoxyPopularData(): EpoxyPopularMovieData.MovieData {
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

fun Set<EpoxyMovie>.toListEpoxyPopularData():List<EpoxyPopularMovieData.MovieData> = map { it.toEpoxyPopularData() }


fun EpoxyMovie.toEpoxyNowPlayingData(): EpoxyNowPlayingMovieData.MovieData {
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

fun Set<EpoxyMovie>.toListEpoxyNowPlayingData():List<EpoxyNowPlayingMovieData.MovieData> = map { it.toEpoxyNowPlayingData() }


fun EpoxyMovie.toEpoxyTopRatedData(): EpoxyTopRatedMovieData.MovieData {
    return EpoxyTopRatedMovieData.MovieData(
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

fun Set<EpoxyMovie>.toListEpoxyTopRatedData():List<EpoxyTopRatedMovieData.MovieData> = map { it.toEpoxyTopRatedData() }


fun EpoxyMovie.toEpoxyUpComingData(): EpoxyUpComingMovieData.MovieData {
    return EpoxyUpComingMovieData.MovieData(
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

fun Set<EpoxyMovie>.toListEpoxyUpComingData():List<EpoxyUpComingMovieData.MovieData> = map { it.toEpoxyUpComingData() }


fun EpoxyMovie.toEpoxySearchMovieData(): EpoxySearchMovieData.MovieData {
    return EpoxySearchMovieData.MovieData(
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

fun Set<EpoxyMovie>.toListEpoxySearchMovieData():List<EpoxySearchMovieData.MovieData> = map { it.toEpoxySearchMovieData() }

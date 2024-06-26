package com.ian.app.muviepedia.core.presentation.model

import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.search.epoxy.EpoxySearchMovieData
import com.ian.app.muviepedia.feature.search.television.EpoxySearchTelevisionData

fun EpoxyMovie.toEpoxyPopularData(): EpoxyPopularMovieData.MovieData {
    return EpoxyPopularMovieData.MovieData(
        epoxyId = epoxyId,
        voteCount = voteCount,
        id = id,
        video = video,
        voteAverage = voteAverage,
        title = title,
        popularity = popularity,
        posterPath = posterPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        backdropPath = backdropPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun Set<EpoxyMovie>.toListEpoxyPopularData(): List<EpoxyPopularMovieData.MovieData> = map { it.toEpoxyPopularData() }

fun EpoxyMovie.toEpoxyNowPlayingData(): EpoxyNowPlayingMovieData.MovieData {
    return EpoxyNowPlayingMovieData.MovieData(
        epoxyId = epoxyId,
        voteCount = voteCount,
        id = id,
        video = video,
        voteAverage = voteAverage,
        title = title,
        popularity = popularity,
        posterPath = posterPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        backdropPath = backdropPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun Set<EpoxyMovie>.toListEpoxyNowPlayingData(): List<EpoxyNowPlayingMovieData.MovieData> = map {
    it.toEpoxyNowPlayingData()
}

fun EpoxyMovie.toEpoxyTopRatedData(): EpoxyTopRatedMovieData.MovieData {
    return EpoxyTopRatedMovieData.MovieData(
        epoxyId = epoxyId,
        voteCount = voteCount,
        id = id,
        video = video,
        voteAverage = voteAverage,
        title = title,
        popularity = popularity,
        posterPath = posterPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        backdropPath = backdropPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun Set<EpoxyMovie>.toListEpoxyTopRatedData(): List<EpoxyTopRatedMovieData.MovieData> = map { it.toEpoxyTopRatedData() }

fun EpoxyMovie.toEpoxyUpComingData(): EpoxyUpComingMovieData.MovieData {
    return EpoxyUpComingMovieData.MovieData(
        epoxyId = epoxyId,
        voteCount = voteCount,
        id = id,
        video = video,
        voteAverage = voteAverage,
        title = title,
        popularity = popularity,
        posterPath = posterPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        backdropPath = backdropPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun Set<EpoxyMovie>.toListEpoxyUpComingData(): List<EpoxyUpComingMovieData.MovieData> = map { it.toEpoxyUpComingData() }

fun EpoxyMovie.toEpoxySearchMovieData(): EpoxySearchMovieData.MovieData {
    return EpoxySearchMovieData.MovieData(
        epoxyId = epoxyId,
        voteCount = voteCount,
        id = id,
        video = video,
        voteAverage = voteAverage,
        title = title,
        popularity = popularity,
        posterPath = posterPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        backdropPath = backdropPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun Set<EpoxyMovie>.toListEpoxySearchMovieData(): List<EpoxySearchMovieData.MovieData> = map {
    it.toEpoxySearchMovieData()
}


fun EpoxyTelevision.toEpoxySearchTelevisionData(): EpoxySearchTelevisionData.TelevisionData {
    return EpoxySearchTelevisionData.TelevisionData(
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

fun Set<EpoxyTelevision>.toListEpoxySearchTelevisionData(): List<EpoxySearchTelevisionData.TelevisionData> = map {
    it.toEpoxySearchTelevisionData()
}

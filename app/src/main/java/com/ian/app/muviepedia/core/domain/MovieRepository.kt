package com.ian.app.muviepedia.core.domain

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.domain.model.DomainSource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun fetchDetailMovie(movieId: Int): Flow<DomainSource<MovieDetail>>

    fun fetchSimilarMovie(movieId: Int): Flow<DomainSource<List<Movie>>>

    fun fetchPopularMovie(): Flow<DomainSource<List<Movie>>>

    fun fetchNowPlayingMovie(): Flow<DomainSource<List<Movie>>>

    fun fetchTopRatedMovie(): Flow<DomainSource<List<Movie>>>

    fun fetchUpComingMovie(): Flow<DomainSource<List<Movie>>>

    fun fetchSearchMovie(query: String): Flow<DomainSource<List<Movie>>>
}

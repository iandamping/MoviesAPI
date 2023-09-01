package com.ian.app.muviepedia.core.data.dataSource.remote.source.movie

import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse

interface MovieRemoteDataSource {

    suspend fun getDetailMovie(movieId: Int): DataSource<DetailMovieResponse>

    suspend fun getSimilarMovie(movieId: Int): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getPopularMovie(): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getNowPlaying(): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getTopRatedMovie(): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getUpComingMovie(): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun searchMovie(userSearch:String): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getNowPlayingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getPopularMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getTopRatedMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getUpComingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>>
}
package com.ian.app.muviepedia.remote

import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.model.BaseResponse
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.model.DataSource

interface MovieRemoteDataSource {

    suspend fun getDetailMovie(movieId: Int): DataSource<DetailMovieData>

    suspend fun getSimilarMovie(movieId: Int): DataSource<BaseResponse<MovieData>>

    suspend fun getPopularMovie(): DataSource<BaseResponse<MovieData>>

    suspend fun getNowPlaying(): DataSource<BaseResponse<MovieData>>

    suspend fun getTopRatedMovie(): DataSource<BaseResponse<MovieData>>

    suspend fun getUpComingMovie(): DataSource<BaseResponse<MovieData>>

    suspend fun getNowPlayingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieData>>

    suspend fun getPopularMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieData>>

    suspend fun getTopRatedMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieData>>

    suspend fun getUpComingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieData>>
}
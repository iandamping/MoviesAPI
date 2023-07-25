package com.ian.app.muviepedia.core.data.dataSource.remote.api

import com.ian.app.muviepedia.data.model.*
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.util.MovieConstant.airingTodayTv
import com.ian.app.muviepedia.util.MovieConstant.detailMovie
import com.ian.app.muviepedia.util.MovieConstant.detailTv
import com.ian.app.muviepedia.util.MovieConstant.nowPlayingMovie
import com.ian.app.muviepedia.util.MovieConstant.onAirTv
import com.ian.app.muviepedia.util.MovieConstant.popularMovie
import com.ian.app.muviepedia.util.MovieConstant.popularTv
import com.ian.app.muviepedia.util.MovieConstant.similarMovie
import com.ian.app.muviepedia.util.MovieConstant.similarTv
import com.ian.app.muviepedia.util.MovieConstant.topRatedMovie
import com.ian.app.muviepedia.util.MovieConstant.topRatedTv
import com.ian.app.muviepedia.util.MovieConstant.upComingMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
interface ApiInterface {
    /*Movie Session*/
    @GET(popularMovie)
    suspend fun getPopularMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieDataResponse>>

    @GET(nowPlayingMovie)
    suspend fun getNowPlayingMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieDataResponse>>

    @GET(topRatedMovie)
    suspend fun getTopRatedMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieDataResponse>>

    @GET(upComingMovie)
    suspend fun getUpComingMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieDataResponse>>

    @GET("$detailMovie{movie}")
    suspend fun getDetailMovieAsync(
        @Path("movie") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailMovieResponse>

    @GET("$similarMovie{movie_id}/similar")
    suspend fun getSimilarMovieAsync(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<BaseResponse<MovieDataResponse>>

    /*Movie Paging session*/
    @GET(popularMovie)
    suspend fun pagingGetPopularMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieDataResponse>>

    @GET(nowPlayingMovie)
    suspend fun pagingGetNowPlayingMovieMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieDataResponse>>

    @GET(topRatedMovie)
    suspend fun pagingGetTopRatedMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieDataResponse>>

    @GET(upComingMovie)
    suspend fun pagingGetUpComingMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieDataResponse>>

    /*Tv Session*/
    @GET(popularTv)
    suspend fun getPopularTvAsync(@Query("api_key") apiKey: String): Response<BaseResponse<TvDataResponse>>

    @GET(topRatedTv)
    suspend fun getTopRatedTvAsync(@Query("api_key") apiKey: String): Response<BaseResponse<TvDataResponse>>

    @GET(airingTodayTv)
    suspend fun getAiringTodayTvAsync(@Query("api_key") apiKey: String): Response<BaseResponse<TvDataResponse>>

    @GET(onAirTv)
    suspend fun getOnAirTvAsync(@Query("api_key") apiKey: String): Response<BaseResponse<TvDataResponse>>

    @GET("$detailTv{tv_id}")
    suspend fun getDetailTvAsync(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailTvResponse>

    @GET("$similarTv{tv_id}/similar")
    suspend fun getSimilarTvAsync(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Response<BaseResponse<TvDataResponse>>

    /*Tv Paging session*/
    @GET(popularTv)
    suspend fun pagingGetPopularTvAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<TvDataResponse>>

    @GET(topRatedTv)
    suspend fun pagingGetTopRatedTvAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<TvDataResponse>>

    @GET(airingTodayTv)
    suspend fun pagingGetAiringTodayTvAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<TvDataResponse>>

    @GET(onAirTv)
    suspend fun pagingGetOnAirTvAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<TvDataResponse>>

}
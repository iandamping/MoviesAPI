package com.ian.app.muviepedia.remote.api

import com.ian.app.muviepedia.data.model.*
import com.ian.app.muviepedia.model.BaseResponse
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
    suspend fun getPopularMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieData>>

    @GET(nowPlayingMovie)
    suspend fun getNowPlayingMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieData>>

    @GET(topRatedMovie)
    suspend fun getTopRatedMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieData>>

    @GET(upComingMovie)
    suspend fun getUpComingMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieData>>

    @GET("$detailMovie{movie}")
    suspend fun getDetailMovieAsync(
        @Path("movie") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailMovieData>

    @GET("$similarMovie{movie_id}/similar")
    suspend fun getSimilarMovieAsync(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<BaseResponse<MovieData>>

    /*Movie Paging session*/
    @GET(popularMovie)
    suspend fun pagingGetPopularMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieData>>

    @GET(nowPlayingMovie)
    suspend fun pagingGetNowPlayingMovieMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieData>>

    @GET(topRatedMovie)
    suspend fun pagingGetTopRatedMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieData>>

    @GET(upComingMovie)
    suspend fun pagingGetUpComingMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieData>>

    /*Tv Session*/
    @GET(popularTv)
    suspend fun getPopularTvAsync(@Query("api_key") apiKey: String): Response<BaseResponse<TvData>>

    @GET(topRatedTv)
    suspend fun getTopRatedTvAsync(@Query("api_key") apiKey: String): Response<BaseResponse<TvData>>

    @GET(airingTodayTv)
    suspend fun getAiringTodayTvAsync(@Query("api_key") apiKey: String): Response<BaseResponse<TvData>>

    @GET(onAirTv)
    suspend fun getOnAirTvAsync(@Query("api_key") apiKey: String): Response<BaseResponse<TvData>>

    @GET("$detailTv{tv_id}")
    suspend fun getDetailTvAsync(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailTvData>

    @GET("$similarTv{tv_id}/similar")
    suspend fun getSimilarTvAsync(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Response<BaseResponse<TvData>>

    /*Tv Paging session*/
    @GET(popularTv)
    suspend fun pagingGetPopularTvAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<TvData>>

    @GET(topRatedTv)
    suspend fun pagingGetTopRatedTvAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<TvData>>

    @GET(airingTodayTv)
    suspend fun pagingGetAiringTodayTvAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<TvData>>

    @GET(onAirTv)
    suspend fun pagingGetOnAirTvAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<TvData>>

}
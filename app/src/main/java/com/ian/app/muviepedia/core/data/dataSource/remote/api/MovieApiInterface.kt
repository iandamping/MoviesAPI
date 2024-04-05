package com.ian.app.muviepedia.core.data.dataSource.remote.api

import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {
    /*Movie Session*/
    @GET(NetworkConstant.popularMovie)
    suspend fun getPopularMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieDataResponse>>

    @GET(NetworkConstant.nowPlayingMovie)
    suspend fun getNowPlayingMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieDataResponse>>

    @GET(NetworkConstant.topRatedMovie)
    suspend fun getTopRatedMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieDataResponse>>

    @GET(NetworkConstant.upComingMovie)
    suspend fun getUpComingMovieAsync(@Query("api_key") apiKey: String): Response<BaseResponse<MovieDataResponse>>

    @GET("${NetworkConstant.detailMovie}{movie}")
    suspend fun getDetailMovieAsync(
        @Path("movie") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailMovieResponse>

    @GET("${NetworkConstant.similarMovie}{movie_id}/similar")
    suspend fun getSimilarMovieAsync(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<BaseResponse<MovieDataResponse>>

    @GET(NetworkConstant.searchMovie)
    suspend fun getSearchMovieResponse(
        @Header("Authorization") token: String,
        @Header("accept") accept: String = "application/json",
        @Query("query") searchMovie: String,
        @Query("include_adult") adult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Response<BaseResponse<MovieDataResponse>>
}

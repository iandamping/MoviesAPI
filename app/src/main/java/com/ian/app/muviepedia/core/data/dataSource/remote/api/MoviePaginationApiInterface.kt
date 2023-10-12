package com.ian.app.muviepedia.core.data.dataSource.remote.api

import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviePaginationApiInterface {

    /*Movie Paging session*/
    @GET(NetworkConstant.popularMovie)
    suspend fun pagingGetPopularMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieDataResponse>>

    @GET(NetworkConstant.nowPlayingMovie)
    suspend fun pagingGetNowPlayingMovieMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieDataResponse>>

    @GET(NetworkConstant.topRatedMovie)
    suspend fun pagingGetTopRatedMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieDataResponse>>

    @GET(NetworkConstant.upComingMovie)
    suspend fun pagingGetUpComingMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MovieDataResponse>>
}

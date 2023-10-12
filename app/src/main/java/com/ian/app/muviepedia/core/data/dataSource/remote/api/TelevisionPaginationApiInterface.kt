package com.ian.app.muviepedia.core.data.dataSource.remote.api

import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.airingTodayTv
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.onAirTv
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.popularTv
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.topRatedTv
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
interface TelevisionPaginationApiInterface {
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

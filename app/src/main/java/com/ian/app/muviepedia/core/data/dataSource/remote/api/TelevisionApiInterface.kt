package com.ian.app.muviepedia.core.data.dataSource.remote.api

import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.airingTodayTv
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.detailTv
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.onAirTv
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.popularTv
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.searchTvShows
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.similarTv
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant.topRatedTv
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
interface TelevisionApiInterface {
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

    @GET(searchTvShows)
    suspend fun getSearchTvResponse(
        @Header("Authorization") token: String,
        @Header("accept") accept: String = "application/json",
//        @Query("api_key") apiKey: String,
        @Query("query") searchMovie: String,
    ): Response<BaseResponse<TvDataResponse>>
}

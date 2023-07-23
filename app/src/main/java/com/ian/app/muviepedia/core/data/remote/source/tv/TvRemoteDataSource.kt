package com.ian.app.muviepedia.core.data.remote.source.tv

import com.ian.app.muviepedia.data.model.DetailTvData
import com.ian.app.muviepedia.core.data.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.remote.model.common.DataSource
import com.ian.app.muviepedia.core.data.remote.model.response.TvDataResponse

interface TvRemoteDataSource {

    suspend fun getDetailTv(tvID: Int): DataSource<DetailTvData>

    suspend fun getSimilarTv(tvID: Int): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getPopularTv(): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getTopRatedTv(): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getAiringTodayTv(): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getOnAirTv(): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getAiringTodayPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getOnAirPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getPopularTvPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getTopRatedPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>>
}
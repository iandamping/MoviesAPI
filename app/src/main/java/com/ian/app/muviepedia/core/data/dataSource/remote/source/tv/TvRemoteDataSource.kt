package com.ian.app.muviepedia.core.data.dataSource.remote.source.tv

import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.VideoDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource

interface TvRemoteDataSource {

    suspend fun getDetailTv(tvID: Int): DataSource<DetailTvResponse>

    suspend fun getSimilarTv(tvID: Int): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getDetailTvVideo(tvID: Int): DataSource<VideoDataResponse>

    suspend fun getPopularTv(): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getTopRatedTv(): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getAiringTodayTv(): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getOnAirTv(): DataSource<BaseResponse<TvDataResponse>>

    suspend fun searchTv(userSearch: String): DataSource<BaseResponse<TvDataResponse>>
}

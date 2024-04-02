package com.ian.app.muviepedia.core.data.dataSource.remote.source.tv

import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource

interface TvPaginationRemoteDataSource {

    suspend fun getAiringTodayPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getOnAirPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getPopularTvPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>>

    suspend fun getTopRatedPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>>
}

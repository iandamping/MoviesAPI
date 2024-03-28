package com.ian.app.muviepedia.core.data.dataSource.remote.source.tv

import com.ian.app.muviepedia.BuildConfig.MOVIE_API_KEY
import com.ian.app.muviepedia.core.data.dataSource.remote.api.TelevisionPaginationApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.di.qualifier.IoDispatcher
import com.ian.app.muviepedia.di.qualifier.TelevisionPagingApiInterfaceQualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvPaginationRemoteDataSourceImpl @Inject constructor(
    @TelevisionPagingApiInterfaceQualifier private val api: TelevisionPaginationApiInterface,
    injectedRemoteHelper: RemoteHelper,
    @IoDispatcher private val customIODispatcher: CoroutineDispatcher
) : RemoteHelper by injectedRemoteHelper, TvPaginationRemoteDataSource {

    override suspend fun getAiringTodayPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(
                        api.pagingGetAiringTodayTvAsync(
                            page = pageTv,
                            apiKey = MOVIE_API_KEY
                        )
                    )
            ) {
                is RemoteBaseResult.Error -> DataSource.Error(data.message)
                is RemoteBaseResult.Success -> {
                    val result = data.data
                    if (result != null) {
                        DataSource.Success(result)
                    } else {
                        DataSource.Error("null body")
                    }
                }
            }
        }
    }

    override suspend fun getOnAirPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(api.pagingGetOnAirTvAsync(page = pageTv, apiKey = MOVIE_API_KEY))
            ) {
                is RemoteBaseResult.Error -> DataSource.Error(data.message)
                is RemoteBaseResult.Success -> {
                    val result = data.data
                    if (result != null) {
                        DataSource.Success(result)
                    } else {
                        DataSource.Error("null body")
                    }
                }
            }
        }
    }

    override suspend fun getPopularTvPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(
                        api.pagingGetPopularTvAsync(
                            page = pageTv,
                            apiKey = MOVIE_API_KEY
                        )
                    )
            ) {
                is RemoteBaseResult.Error -> DataSource.Error(data.message)
                is RemoteBaseResult.Success -> {
                    val result = data.data
                    if (result != null) {
                        DataSource.Success(result)
                    } else {
                        DataSource.Error("null body")
                    }
                }
            }
        }
    }

    override suspend fun getTopRatedPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(
                        api.pagingGetTopRatedTvAsync(
                            page = pageTv,
                            apiKey = MOVIE_API_KEY
                        )
                    )
            ) {
                is RemoteBaseResult.Error -> DataSource.Error(data.message)
                is RemoteBaseResult.Success -> {
                    val result = data.data
                    if (result != null) {
                        DataSource.Success(result)
                    } else {
                        DataSource.Error("null body")
                    }
                }
            }
        }
        }
}

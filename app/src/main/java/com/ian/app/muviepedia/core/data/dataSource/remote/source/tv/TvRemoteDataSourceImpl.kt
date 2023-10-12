package com.ian.app.muviepedia.core.data.dataSource.remote.source.tv

import com.ian.app.muviepedia.BuildConfig.MOVIE_API_KEY
import com.ian.app.muviepedia.core.data.dataSource.remote.api.TelevisionApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.core.data.model.RemoteResult
import com.ian.app.muviepedia.di.qualifier.TelevisionApiInterfaceQualifier
import javax.inject.Inject

class TvRemoteDataSourceImpl @Inject constructor(
    @TelevisionApiInterfaceQualifier private val api: TelevisionApiInterface,
    injectedRemoteHelper: RemoteHelper
) : RemoteHelper by injectedRemoteHelper, TvRemoteDataSource {
    override suspend fun getDetailTv(tvID: Int): DataSource<DetailTvResponse> {
        return when (
            val data =
                remoteCall(api.getDetailTvAsync(tvId = tvID, apiKey = MOVIE_API_KEY))
        ) {
            is RemoteResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else {
                    DataSource.Error("null body")
                }
            }
        }
    }

    override suspend fun getSimilarTv(tvID: Int): DataSource<BaseResponse<TvDataResponse>> {
        return when (
            val data =
                remoteWithBaseCall(api.getSimilarTvAsync(tvId = tvID, apiKey = MOVIE_API_KEY))
        ) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else {
                    DataSource.Error("null body")
                }
            }
        }
    }

    override suspend fun getPopularTv(): DataSource<BaseResponse<TvDataResponse>> {
        return when (
            val data =
                remoteWithBaseCall(api.getPopularTvAsync(apiKey = MOVIE_API_KEY))
        ) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else {
                    DataSource.Error("null body")
                }
            }
        }
    }

    override suspend fun getTopRatedTv(): DataSource<BaseResponse<TvDataResponse>> {
        return when (
            val data =
                remoteWithBaseCall(api.getTopRatedTvAsync(apiKey = MOVIE_API_KEY))
        ) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else {
                    DataSource.Error("null body")
                }
            }
        }
    }

    override suspend fun getAiringTodayTv(): DataSource<BaseResponse<TvDataResponse>> {
        return when (
            val data =
                remoteWithBaseCall(api.getAiringTodayTvAsync(apiKey = MOVIE_API_KEY))
        ) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else {
                    DataSource.Error("null body")
                }
            }
        }
    }

    override suspend fun getOnAirTv(): DataSource<BaseResponse<TvDataResponse>> {
        return when (
            val data =
                remoteWithBaseCall(api.getOnAirTvAsync(apiKey = MOVIE_API_KEY))
        ) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else {
                    DataSource.Error("null body")
                }
            }
        }
    }
}

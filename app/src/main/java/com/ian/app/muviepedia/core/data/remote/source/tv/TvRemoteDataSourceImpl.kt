package com.ian.app.muviepedia.core.data.remote.source.tv

import com.ian.app.muviepedia.data.model.DetailTvData
import com.ian.app.muviepedia.core.data.remote.api.ApiInterface
import com.ian.app.muviepedia.core.data.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.remote.model.common.DataSource
import com.ian.app.muviepedia.core.data.remote.model.common.RemoteBaseResult
import com.ian.app.muviepedia.core.data.remote.model.common.RemoteResult
import com.ian.app.muviepedia.core.data.remote.model.response.TvDataResponse
import javax.inject.Inject

class TvRemoteDataSourceImpl @Inject constructor(
    private val api: ApiInterface,
    injectedRemoteHelper: RemoteHelper
) : RemoteHelper by injectedRemoteHelper, TvRemoteDataSource {
    override suspend fun getDetailTv(tvID: Int): DataSource<DetailTvData> {
        return when (val data =
            remoteCall(api.getDetailTvAsync(tvId = tvID, apiKey = ""))) {
            is RemoteResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getSimilarTv(tvID: Int): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.getSimilarTvAsync(tvId = tvID, apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getPopularTv(): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.getPopularTvAsync(apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getTopRatedTv(): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.getTopRatedTvAsync(apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getAiringTodayTv(): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.getAiringTodayTvAsync(apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getOnAirTv(): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.getOnAirTvAsync(apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getAiringTodayPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.pagingGetAiringTodayTvAsync(page = pageTv, apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getOnAirPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.pagingGetOnAirTvAsync(page = pageTv, apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getPopularTvPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.pagingGetPopularTvAsync(page = pageTv, apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getTopRatedPaging(pageTv: Int): DataSource<BaseResponse<TvDataResponse>> {
        return when (val data =
            remoteWithBaseCall(api.pagingGetTopRatedTvAsync(page = pageTv, apiKey = ""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }
}
package com.ian.app.muviepedia.core.data.dataSource.remote.source.tv

import com.ian.app.muviepedia.BuildConfig
import com.ian.app.muviepedia.BuildConfig.MOVIE_API_KEY
import com.ian.app.muviepedia.core.data.dataSource.remote.api.TelevisionApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.ErrorResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.VideoDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.core.data.model.RemoteResult
import com.ian.app.muviepedia.core.presentation.model.CommonResult
import com.ian.app.muviepedia.di.qualifier.IoDispatcher
import com.ian.app.muviepedia.di.qualifier.TelevisionApiInterfaceQualifier
import com.ian.app.muviepedia.util.parser.MoshiParser
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvRemoteDataSourceImpl @Inject constructor(
    @TelevisionApiInterfaceQualifier private val api: TelevisionApiInterface,
    @IoDispatcher private val customIODispatcher: CoroutineDispatcher,
    private val moshiParser: MoshiParser,
    injectedRemoteHelper: RemoteHelper,
) : RemoteHelper by injectedRemoteHelper, TvRemoteDataSource {
    override suspend fun getDetailTv(tvID: Int): DataSource<DetailTvResponse> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteCall(api.getDetailTvAsync(tvId = tvID, apiKey = MOVIE_API_KEY))
            ) {
                is RemoteResult.Error -> DataSource.Error(data.message)
                is RemoteResult.Success -> {
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

    override suspend fun getSimilarTv(tvID: Int): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(api.getSimilarTvAsync(tvId = tvID, apiKey = MOVIE_API_KEY))
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

    override suspend fun getPopularTv(): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(api.getPopularTvAsync(apiKey = MOVIE_API_KEY))
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

    override suspend fun getTopRatedTv(): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(api.getTopRatedTvAsync(apiKey = MOVIE_API_KEY))
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

    override suspend fun getAiringTodayTv(): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(api.getAiringTodayTvAsync(apiKey = MOVIE_API_KEY))
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

    override suspend fun getOnAirTv(): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteWithBaseCall(api.getOnAirTvAsync(apiKey = MOVIE_API_KEY))
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

    override suspend fun getDetailTvVideo(tvID: Int): DataSource<VideoDataResponse> {
        return withContext(customIODispatcher) {
            when (
                val data =
                    remoteCall(
                        api.getVideosTvAsync(
                            tvId = tvID,
                            token = "Bearer ${BuildConfig.ACCESS_TOKEN_KEY}",
                        )
                    )
            ) {
                is RemoteResult.Error -> DataSource.Error(data.message)
                is RemoteResult.Success -> {
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

    override suspend fun searchTv(userSearch: String): DataSource<BaseResponse<TvDataResponse>> {
        return withContext(customIODispatcher) {
            when (
                val data =
                    remoteWithBaseCall(
                        api.getSearchTvResponse(
                            token = "Bearer ${BuildConfig.ACCESS_TOKEN_KEY}",
                            searchMovie = userSearch
                        )
                    )
            ) {
                is RemoteBaseResult.Error -> {
                    val types = Types.newParameterizedType(
                        ErrorResponse::class.java, ErrorResponse::class.java
                    )

                    when (val parsingResult =
                        moshiParser.fromJson<ErrorResponse>(data.message, types)) {
                        is CommonResult.Failed -> DataSource.Error(parsingResult.errorMessage)
                        is CommonResult.Success -> DataSource.Error(parsingResult.data.message)
                    }

                }

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

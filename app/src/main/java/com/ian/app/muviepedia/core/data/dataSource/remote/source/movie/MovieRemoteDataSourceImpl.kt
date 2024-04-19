package com.ian.app.muviepedia.core.data.dataSource.remote.source.movie

import com.ian.app.muviepedia.BuildConfig.ACCESS_TOKEN_KEY
import com.ian.app.muviepedia.BuildConfig.MOVIE_API_KEY
import com.ian.app.muviepedia.core.data.dataSource.remote.api.MovieApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.ErrorResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.VideoDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.core.data.model.RemoteResult
import com.ian.app.muviepedia.core.presentation.model.CommonResult
import com.ian.app.muviepedia.di.qualifier.IoDispatcher
import com.ian.app.muviepedia.di.qualifier.MovieApiInterfaceQualifier
import com.ian.app.muviepedia.util.parser.MoshiParser
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    @MovieApiInterfaceQualifier private val api: MovieApiInterface,
    @IoDispatcher private val customIODispatcher: CoroutineDispatcher,
    private val moshiParser: MoshiParser,
    injectedRemoteHelper: RemoteHelper,
) : RemoteHelper by injectedRemoteHelper, MovieRemoteDataSource {

    override suspend fun getDetailMovie(movieId: Int): DataSource<DetailMovieResponse> {
        return withContext(customIODispatcher){
            when (
                val data =
                    remoteCall(api.getDetailMovieAsync(movieId = movieId, apiKey = MOVIE_API_KEY))
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

    override suspend fun getSimilarMovie(movieId: Int): DataSource<BaseResponse<MovieDataResponse>> {
        return withContext(customIODispatcher){
            when (
                val data = remoteWithBaseCall(
                    api.getSimilarMovieAsync(
                        movieId = movieId,
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

    override suspend fun getPopularMovie(): DataSource<BaseResponse<MovieDataResponse>> {
        return withContext(customIODispatcher){
            when (val data = remoteWithBaseCall(api.getPopularMovieAsync(MOVIE_API_KEY))) {
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

    override suspend fun getNowPlaying(): DataSource<BaseResponse<MovieDataResponse>> {
        return withContext(customIODispatcher){
            when (val data = remoteWithBaseCall(api.getNowPlayingMovieAsync(MOVIE_API_KEY))) {
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

    override suspend fun getTopRatedMovie(): DataSource<BaseResponse<MovieDataResponse>> {
        return withContext(customIODispatcher){
            when (val data = remoteWithBaseCall(api.getTopRatedMovieAsync(MOVIE_API_KEY))) {
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

    override suspend fun getUpComingMovie(): DataSource<BaseResponse<MovieDataResponse>> {
        return withContext(customIODispatcher){
            when (val data = remoteWithBaseCall(api.getUpComingMovieAsync(MOVIE_API_KEY))) {
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

    override suspend fun getDetailVideoMovie(movieId: Int): DataSource<VideoDataResponse> {
        return withContext(customIODispatcher) {
            when (
                val data =
                    remoteCall(
                        api.getVideoMovieAsync(
                            movieId = movieId,
                            token = "Bearer $ACCESS_TOKEN_KEY",
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

    override suspend fun searchMovie(userSearch: String): DataSource<BaseResponse<MovieDataResponse>> {
        return withContext(customIODispatcher) {
            when (
                val data =
                    remoteWithBaseCall(
                        api.getSearchMovieResponse(
                            token = "Bearer $ACCESS_TOKEN_KEY",
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

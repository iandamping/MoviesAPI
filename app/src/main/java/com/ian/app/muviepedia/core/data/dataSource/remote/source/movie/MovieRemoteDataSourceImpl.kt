package com.ian.app.muviepedia.core.data.dataSource.remote.source.movie

import com.ian.app.muviepedia.BuildConfig.ACCESS_TOKEN_KEY
import com.ian.app.muviepedia.BuildConfig.MOVIE_API_KEY
import com.ian.app.muviepedia.core.data.dataSource.remote.api.ApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.core.data.model.RemoteResult
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: ApiInterface,
    injectedRemoteHelper: RemoteHelper
) : RemoteHelper by injectedRemoteHelper, MovieRemoteDataSource {

    override suspend fun getDetailMovie(movieId: Int): DataSource<DetailMovieResponse> {
        return when (
            val data =
                remoteCall(api.getDetailMovieAsync(movieId = movieId, apiKey = MOVIE_API_KEY))
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

    override suspend fun getSimilarMovie(movieId: Int): DataSource<BaseResponse<MovieDataResponse>> {
        return when (
            val data = remoteWithBaseCall(
                api.getSimilarMovieAsync(
                    movieId = movieId,
                    apiKey = MOVIE_API_KEY
                )
            )
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

    override suspend fun getPopularMovie(): DataSource<BaseResponse<MovieDataResponse>> {
        return when (val data = remoteWithBaseCall(api.getPopularMovieAsync(MOVIE_API_KEY))) {
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

    override suspend fun getNowPlaying(): DataSource<BaseResponse<MovieDataResponse>> {
        return when (val data = remoteWithBaseCall(api.getNowPlayingMovieAsync(MOVIE_API_KEY))) {
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

    override suspend fun getTopRatedMovie(): DataSource<BaseResponse<MovieDataResponse>> {
        return when (val data = remoteWithBaseCall(api.getTopRatedMovieAsync(MOVIE_API_KEY))) {
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

    override suspend fun getUpComingMovie(): DataSource<BaseResponse<MovieDataResponse>> {
        return when (val data = remoteWithBaseCall(api.getUpComingMovieAsync(MOVIE_API_KEY))) {
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

    override suspend fun searchMovie(userSearch: String): DataSource<BaseResponse<MovieDataResponse>> {
        return when (
            val data =
                remoteWithBaseCall(
                    api.getSearchMovieResponse(
//                    apiKey = MOVIE_API_KEY,
                        token = "Bearer $ACCESS_TOKEN_KEY",
                        searchMovie = userSearch
                    )
                )
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

    override suspend fun getNowPlayingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>> {
        return when (
            val data = remoteWithBaseCall(
                api.pagingGetNowPlayingMovieMovieAsync(
                    apiKey = MOVIE_API_KEY,
                    page = pageMovie
                )
            )
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

    override suspend fun getPopularMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>> {
        return when (
            val data = remoteWithBaseCall(
                api.pagingGetPopularMovieAsync(
                    apiKey = MOVIE_API_KEY,
                    page = pageMovie
                )
            )
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

    override suspend fun getTopRatedMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>> {
        return when (
            val data = remoteWithBaseCall(
                api.pagingGetTopRatedMovieAsync(
                    apiKey = MOVIE_API_KEY,
                    page = pageMovie
                )
            )
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

    override suspend fun getUpComingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>> {
        return when (
            val data = remoteWithBaseCall(
                api.pagingGetUpComingMovieAsync(
                    apiKey = MOVIE_API_KEY,
                    page = pageMovie
                )
            )
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

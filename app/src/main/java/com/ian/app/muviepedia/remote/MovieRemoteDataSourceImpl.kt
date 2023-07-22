package com.ian.app.muviepedia.remote

import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.model.BaseResponse
import com.ian.app.muviepedia.model.DataSource
import com.ian.app.muviepedia.model.RemoteBaseResult
import com.ian.app.muviepedia.model.RemoteResult
import com.ian.app.muviepedia.remote.api.ApiInterface
import com.ian.app.muviepedia.remote.helper.RemoteHelper
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: ApiInterface,
    injectedRemoteHelper: RemoteHelper
) : RemoteHelper by injectedRemoteHelper, MovieRemoteDataSource {

    override suspend fun getDetailMovie(movieId: Int): DataSource<DetailMovieData> {
        return when (val data =
            remoteCall(api.getDetailMovieAsync(movieId = movieId, apiKey = ""))) {
            is RemoteResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getSimilarMovie(movieId: Int): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(
            api.getSimilarMovieAsync(
                movieId = movieId,
                apiKey = ""
            )
        )) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getPopularMovie(): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(api.getPopularMovieAsync(""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getNowPlaying(): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(api.getNowPlayingMovieAsync(""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getTopRatedMovie(): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(api.getTopRatedMovieAsync(""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getUpComingMovie(): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(api.getUpComingMovieAsync(""))) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getNowPlayingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(
            api.pagingGetNowPlayingMovieMovieAsync(
                apiKey = "",
                page = pageMovie
            )
        )) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getPopularMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(
            api.pagingGetPopularMovieAsync(
                apiKey = "",
                page = pageMovie
            )
        )) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getTopRatedMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(
            api.pagingGetTopRatedMovieAsync(
                apiKey = "",
                page = pageMovie
            )
        )) {
            is RemoteBaseResult.Error -> DataSource.Error(data.exception.message ?: "")
            is RemoteBaseResult.Success -> {
                val result = data.data.body()
                if (result != null) {
                    DataSource.Success(result)
                } else DataSource.Error("null body")
            }
        }
    }

    override suspend fun getUpComingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieData>> {
        return when (val data = remoteWithBaseCall(
            api.pagingGetUpComingMovieAsync(
                apiKey = "",
                page = pageMovie
            )
        )) {
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
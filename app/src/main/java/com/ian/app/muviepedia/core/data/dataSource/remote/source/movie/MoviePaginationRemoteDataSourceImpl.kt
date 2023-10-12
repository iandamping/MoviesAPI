package com.ian.app.muviepedia.core.data.dataSource.remote.source.movie

import com.ian.app.muviepedia.BuildConfig.MOVIE_API_KEY
import com.ian.app.muviepedia.core.data.dataSource.remote.api.MoviePaginationApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.di.qualifier.MoviePagingApiInterfaceQualifier
import javax.inject.Inject

class MoviePaginationRemoteDataSourceImpl @Inject constructor(
    @MoviePagingApiInterfaceQualifier private val api: MoviePaginationApiInterface,
    injectedRemoteHelper: RemoteHelper
) : RemoteHelper by injectedRemoteHelper, MoviePaginationRemoteDataSource {

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

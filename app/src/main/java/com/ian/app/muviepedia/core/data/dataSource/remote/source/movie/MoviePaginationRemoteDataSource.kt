package com.ian.app.muviepedia.core.data.dataSource.remote.source.movie

import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource

interface MoviePaginationRemoteDataSource {

    suspend fun getNowPlayingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getPopularMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getTopRatedMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>>

    suspend fun getUpComingMoviePaging(pageMovie: Int): DataSource<BaseResponse<MovieDataResponse>>
}

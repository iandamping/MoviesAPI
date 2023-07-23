package com.ian.app.muviepedia.di.module.data.remote

import com.ian.app.muviepedia.core.data.remote.source.movie.MovieRemoteDataSource
import com.ian.app.muviepedia.core.data.remote.source.movie.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface MovieRemoteDataSourceModule {
    @Binds
    fun bindsMovieRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}
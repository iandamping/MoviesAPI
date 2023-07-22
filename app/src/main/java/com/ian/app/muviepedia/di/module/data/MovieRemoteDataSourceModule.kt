package com.ian.app.muviepedia.di.module.data

import com.ian.app.muviepedia.di.scope.ApplicationScoped
import com.ian.app.muviepedia.remote.MovieRemoteDataSource
import com.ian.app.muviepedia.remote.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface MovieRemoteDataSourceModule {

    @Binds
    @ApplicationScoped
    fun bindsMovieRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}
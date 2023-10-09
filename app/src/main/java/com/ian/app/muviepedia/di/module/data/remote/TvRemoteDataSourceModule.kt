package com.ian.app.muviepedia.di.module.data.remote

import com.ian.app.muviepedia.core.data.dataSource.remote.source.tv.TvRemoteDataSource
import com.ian.app.muviepedia.core.data.dataSource.remote.source.tv.TvRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface TvRemoteDataSourceModule {

    @Binds
    fun bindsTvRemoteDataSource(impl: TvRemoteDataSourceImpl): TvRemoteDataSource
}

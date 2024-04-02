package com.ian.app.muviepedia.di.module.data.local

import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface TvLocalDataSourceModule {
    @Binds
    fun bindsTvLocalDataSource(impl: TvLocalDataSourceImpl): TvLocalDataSource
}

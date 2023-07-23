package com.ian.app.muviepedia.di.module.data.local

import com.ian.app.muviepedia.core.data.cache.source.MovieLocalDataSource
import com.ian.app.muviepedia.core.data.cache.source.MovieLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface MovieLocalDataSourceModule {

    @Binds
    fun bindsMovieLocalDataSource(impl: MovieLocalDataSourceImpl): MovieLocalDataSource
}
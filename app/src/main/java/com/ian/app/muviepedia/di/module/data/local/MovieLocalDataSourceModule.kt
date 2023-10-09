package com.ian.app.muviepedia.di.module.data.local

import com.ian.app.muviepedia.core.data.dataSource.cache.source.movie.MovieLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.cache.source.movie.MovieLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface MovieLocalDataSourceModule {
    @Binds
    fun bindsMovieLocalDataSource(impl: MovieLocalDataSourceImpl): MovieLocalDataSource
}

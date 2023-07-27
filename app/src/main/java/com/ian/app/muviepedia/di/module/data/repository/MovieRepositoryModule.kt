package com.ian.app.muviepedia.di.module.data.repository

import com.ian.app.muviepedia.core.data.repository.MovieRepositoryImpl
import com.ian.app.muviepedia.core.domain.MovieRepository
import dagger.Binds
import dagger.Module

@Module
interface MovieRepositoryModule {
    @Binds
    fun bindsMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}
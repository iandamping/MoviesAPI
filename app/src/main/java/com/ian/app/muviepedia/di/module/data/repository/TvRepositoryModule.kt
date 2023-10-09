package com.ian.app.muviepedia.di.module.data.repository

import com.ian.app.muviepedia.core.data.repository.TvRepositoryImpl
import com.ian.app.muviepedia.core.domain.TvRepository
import dagger.Binds
import dagger.Module

@Module
interface TvRepositoryModule {

    @Binds
    fun bindsTvRepository(impl: TvRepositoryImpl): TvRepository
}

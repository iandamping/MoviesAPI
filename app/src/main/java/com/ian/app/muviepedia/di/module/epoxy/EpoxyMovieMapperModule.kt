package com.ian.app.muviepedia.di.module.epoxy

import com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.movie.EpoxyMovieMapper
import com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.movie.EpoxyMovieMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface EpoxyMovieMapperModule {

    @Binds
    fun bindsEpoxyMapper(impl: EpoxyMovieMapperImpl): EpoxyMovieMapper
}

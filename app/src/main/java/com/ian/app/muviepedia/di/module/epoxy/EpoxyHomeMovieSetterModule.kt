package com.ian.app.muviepedia.di.module.epoxy

import com.ian.app.muviepedia.core.presentation.epoxyMapper.home.movie.EpoxyHomeMovieScreenSetter
import com.ian.app.muviepedia.core.presentation.epoxyMapper.home.movie.EpoxyHomeMovieScreenSetterImpl
import dagger.Binds
import dagger.Module

@Module
interface EpoxyHomeMovieSetterModule {

    @Binds
    fun bindsEpoxyHomeMovieSetter(impl: EpoxyHomeMovieScreenSetterImpl): EpoxyHomeMovieScreenSetter
}

package com.ian.app.muviepedia.di.module.epoxy

import com.ian.app.muviepedia.core.presentation.EpoxyHomeMovieSetter
import com.ian.app.muviepedia.core.presentation.EpoxyHomeMovieSetterImpl
import dagger.Binds
import dagger.Module

@Module
interface EpoxyHomeMovieSetterModule {

    @Binds
    fun bindsEpoxyHomeMovieSetter(impl: EpoxyHomeMovieSetterImpl): EpoxyHomeMovieSetter
}

package com.ian.app.muviepedia.di.module.epoxy

import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.core.presentation.EpoxyMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface EpoxyMapperModule {

    @Binds
    fun bindsEpoxyMapper(impl: EpoxyMapperImpl): EpoxyMapper
}

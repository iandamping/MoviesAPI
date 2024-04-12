package com.ian.app.muviepedia.di.module.epoxy

import com.ian.app.muviepedia.core.presentation.epoxyMapper.home.television.EpoxyHomeTelevisionScreenSetter
import com.ian.app.muviepedia.core.presentation.epoxyMapper.home.television.EpoxyHomeTelevisionScreenSetterImpl
import dagger.Binds
import dagger.Module

@Module
interface EpoxyHomeTelevisionSetterModule {

    @Binds
    fun bindsEpoxyHomeTelevisionSetterModule(impl: EpoxyHomeTelevisionScreenSetterImpl): EpoxyHomeTelevisionScreenSetter
}

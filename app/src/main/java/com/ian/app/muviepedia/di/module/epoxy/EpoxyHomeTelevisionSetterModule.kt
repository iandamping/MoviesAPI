package com.ian.app.muviepedia.di.module.epoxy

import com.ian.app.muviepedia.core.presentation.EpoxyHomeTelevisionSetter
import com.ian.app.muviepedia.core.presentation.EpoxyHomeTelevisionSetterImpl
import dagger.Binds
import dagger.Module

@Module
interface EpoxyHomeTelevisionSetterModule {

    @Binds
    fun bindsEpoxyHomeTelevisionSetterModule(impl: EpoxyHomeTelevisionSetterImpl): EpoxyHomeTelevisionSetter
}

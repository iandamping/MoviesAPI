package com.ian.app.muviepedia.di.module.epoxy

import com.ian.app.muviepedia.core.presentation.epoxyMapper.detail.EpoxyDetailScreenSetter
import com.ian.app.muviepedia.core.presentation.epoxyMapper.detail.EpoxyDetailScreenSetterImpl
import dagger.Binds
import dagger.Module

@Module
interface EpoxyDetailScreenSetterModule {

    @Binds
    fun bindsEpoxyDetailScreenSetter(impl: EpoxyDetailScreenSetterImpl): EpoxyDetailScreenSetter
}
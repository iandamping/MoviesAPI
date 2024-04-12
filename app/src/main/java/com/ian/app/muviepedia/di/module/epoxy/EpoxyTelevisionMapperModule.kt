package com.ian.app.muviepedia.di.module.epoxy

import com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.television.EpoxyTelevisionMapper
import com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.television.EpoxyTelevisionMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface EpoxyTelevisionMapperModule {

    @Binds
    fun bindsEpoxyTelevisionMapper(impl: EpoxyTelevisionMapperImpl): EpoxyTelevisionMapper
}
package com.ian.app.muviepedia.di.module

import com.ian.app.muviepedia.util.UtilityHelper
import com.ian.app.muviepedia.util.UtilityHelperImpl
import dagger.Binds
import dagger.Module

@Module
interface UtilityHelperModule {

    @Binds
    fun bindsUtilityHelper(impl: UtilityHelperImpl): UtilityHelper
}
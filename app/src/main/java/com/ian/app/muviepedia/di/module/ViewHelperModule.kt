package com.ian.app.muviepedia.di.module

import com.ian.app.muviepedia.util.viewHelper.ViewHelper
import com.ian.app.muviepedia.util.viewHelper.ViewHelperImpl
import dagger.Binds
import dagger.Module

@Module
interface ViewHelperModule {

    @Binds
    fun bindsViewHelper(impl: ViewHelperImpl): ViewHelper
}

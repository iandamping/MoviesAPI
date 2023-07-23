package com.ian.app.muviepedia.di.module

import com.ian.app.muviepedia.core.data.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.remote.helper.RemoteHelperImpl
import dagger.Binds
import dagger.Module

@Module
interface RemoteHelperModule {

    @Binds
    fun bindsRemoteHelper(impl: RemoteHelperImpl): RemoteHelper
}
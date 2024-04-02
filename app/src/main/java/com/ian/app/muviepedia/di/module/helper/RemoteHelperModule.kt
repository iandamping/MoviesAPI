package com.ian.app.muviepedia.di.module.helper

import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelperImpl
import dagger.Binds
import dagger.Module

@Module
interface RemoteHelperModule {

    @Binds
    fun bindsRemoteHelper(impl: RemoteHelperImpl): RemoteHelper
}

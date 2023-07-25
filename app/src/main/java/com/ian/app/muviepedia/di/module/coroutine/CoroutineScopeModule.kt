package com.ian.app.muviepedia.di.module.coroutine

import com.ian.app.muviepedia.di.qualifier.CustomDefaultScope
import com.ian.app.muviepedia.di.qualifier.CustomIOScope
import com.ian.app.muviepedia.di.qualifier.CustomMainScope
import com.ian.app.muviepedia.di.qualifier.CustomNonDispatcherScope
import com.ian.app.muviepedia.di.qualifier.DefaultDispatcher
import com.ian.app.muviepedia.di.qualifier.IoDispatcher
import com.ian.app.muviepedia.di.qualifier.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


@Module
object CoroutineScopeModule {

    @Provides
    @CustomNonDispatcherScope
    fun providesApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob())

    @Provides
    @CustomDefaultScope
    fun providesDefaultApplicationScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @Provides
    @CustomIOScope
    fun providesIoApplicationScope(
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)


    @Provides
    @CustomMainScope
    fun providesMainApplicationScope(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + mainDispatcher)
}
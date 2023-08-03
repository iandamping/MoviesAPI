package com.ian.app.muviepedia.di.component

import android.app.Application
import com.ian.app.muviepedia.di.module.ApplicationContextModule
import com.ian.app.muviepedia.di.module.DatabaseModule
import com.ian.app.muviepedia.di.module.MoshiModule
import com.ian.app.muviepedia.di.module.NetworkModule
import com.ian.app.muviepedia.di.module.RemoteHelperModule
import com.ian.app.muviepedia.di.module.UtilityHelperModule
import com.ian.app.muviepedia.di.module.ViewModelFactoryModule
import com.ian.app.muviepedia.di.module.ViewModelModule
import com.ian.app.muviepedia.di.module.coroutine.CoroutineModule
import com.ian.app.muviepedia.di.module.coroutine.CoroutineScopeModule
import com.ian.app.muviepedia.di.module.data.local.MovieLocalDataSourceModule
import com.ian.app.muviepedia.di.module.data.local.TvLocalDataSourceModule
import com.ian.app.muviepedia.di.module.data.remote.MovieRemoteDataSourceModule
import com.ian.app.muviepedia.di.module.data.remote.TvRemoteDataSourceModule
import com.ian.app.muviepedia.di.module.data.repository.MovieRepositoryModule
import com.ian.app.muviepedia.di.module.data.repository.TvRepositoryModule
import com.ian.app.muviepedia.di.module.epoxy.EpoxyMapperModule
import com.ian.app.muviepedia.di.module.sub.ActivitySubComponentModule
import com.ian.app.muviepedia.di.scope.ApplicationScoped
import dagger.BindsInstance
import dagger.Component

@ApplicationScoped
@Component(
    modules = [
        ApplicationContextModule::class,
        CoroutineModule::class,
        CoroutineScopeModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        MoshiModule::class,
        RemoteHelperModule::class,
        UtilityHelperModule::class,
        MovieRemoteDataSourceModule::class,
        MovieLocalDataSourceModule::class,
        TvLocalDataSourceModule::class,
        TvRemoteDataSourceModule::class,
        MovieRepositoryModule::class,
        TvRepositoryModule::class,
        EpoxyMapperModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        ActivitySubComponentModule::class
    ]
)
interface ApplicationComponent {

    fun provideActivityComponentFactory(): ActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun injectApplication(@BindsInstance application: Application): ApplicationComponent
    }
}
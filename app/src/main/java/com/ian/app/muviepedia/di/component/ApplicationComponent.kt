package com.ian.app.muviepedia.di.component

import android.app.Application
import com.ian.app.muviepedia.di.module.context.ApplicationContextModule
import com.ian.app.muviepedia.di.module.coroutine.CoroutineModule
import com.ian.app.muviepedia.di.module.coroutine.CoroutineScopeModule
import com.ian.app.muviepedia.di.module.data.local.MovieLocalDataSourceModule
import com.ian.app.muviepedia.di.module.data.local.TvLocalDataSourceModule
import com.ian.app.muviepedia.di.module.data.remote.MovieRemoteDataSourceModule
import com.ian.app.muviepedia.di.module.data.remote.TvRemoteDataSourceModule
import com.ian.app.muviepedia.di.module.data.repository.MovieRepositoryModule
import com.ian.app.muviepedia.di.module.data.repository.TvRepositoryModule
import com.ian.app.muviepedia.di.module.database.DatabaseModule
import com.ian.app.muviepedia.di.module.epoxy.EpoxyDetailScreenSetterModule
import com.ian.app.muviepedia.di.module.epoxy.EpoxyHomeMovieSetterModule
import com.ian.app.muviepedia.di.module.epoxy.EpoxyHomeTelevisionSetterModule
import com.ian.app.muviepedia.di.module.epoxy.EpoxyMovieMapperModule
import com.ian.app.muviepedia.di.module.epoxy.EpoxyTelevisionMapperModule
import com.ian.app.muviepedia.di.module.helper.MoshiParserModule
import com.ian.app.muviepedia.di.module.helper.RemoteHelperModule
import com.ian.app.muviepedia.di.module.helper.UtilityHelperModule
import com.ian.app.muviepedia.di.module.moshi.MoshiModule
import com.ian.app.muviepedia.di.module.network.NetworkModule
import com.ian.app.muviepedia.di.module.sub.ActivitySubComponentModule
import com.ian.app.muviepedia.di.module.viewModel.ViewModelFactoryModule
import com.ian.app.muviepedia.di.module.viewModel.ViewModelModule
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
        MoshiParserModule::class,
        MovieRemoteDataSourceModule::class,
        MovieLocalDataSourceModule::class,
        TvLocalDataSourceModule::class,
        TvRemoteDataSourceModule::class,
        MovieRepositoryModule::class,
        TvRepositoryModule::class,
        EpoxyMovieMapperModule::class,
        EpoxyTelevisionMapperModule::class,
        EpoxyHomeMovieSetterModule::class,
        EpoxyDetailScreenSetterModule::class,
        EpoxyHomeTelevisionSetterModule::class,
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

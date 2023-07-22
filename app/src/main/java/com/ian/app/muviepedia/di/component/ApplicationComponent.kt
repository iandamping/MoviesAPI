package com.ian.app.muviepedia.di.component

import android.app.Application
import com.ian.app.muviepedia.di.module.ApplicationContextModule
import com.ian.app.muviepedia.di.module.MoshiModule
import com.ian.app.muviepedia.di.module.NetworkModule
import com.ian.app.muviepedia.di.module.RemoteHelperModule
import com.ian.app.muviepedia.di.module.UtilityHelperModule
import com.ian.app.muviepedia.di.module.data.MovieRemoteDataSourceModule
import com.ian.app.muviepedia.di.scope.ApplicationScoped
import dagger.BindsInstance
import dagger.Component

@ApplicationScoped
@Component(
    modules = [
        ApplicationContextModule::class,
        NetworkModule::class,
        MoshiModule::class,
        RemoteHelperModule::class,
        UtilityHelperModule::class,
        MovieRemoteDataSourceModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun injectApplication(@BindsInstance application: Application): ApplicationComponent
    }
}
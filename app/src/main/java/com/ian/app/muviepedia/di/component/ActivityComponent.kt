package com.ian.app.muviepedia.di.component

import androidx.fragment.app.FragmentActivity
import com.ian.app.muviepedia.di.module.ActivityContextModule
import com.ian.app.muviepedia.di.module.ActivityModule
import com.ian.app.muviepedia.di.module.sub.FragmentSubComponentModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ActivityContextModule::class,
        ActivityModule::class,
        FragmentSubComponentModule::class
    ]
)
interface ActivityComponent {

    fun provideFragmentComponent(): FragmentComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun injectActivity(@BindsInstance activity: FragmentActivity): ActivityComponent
    }
}
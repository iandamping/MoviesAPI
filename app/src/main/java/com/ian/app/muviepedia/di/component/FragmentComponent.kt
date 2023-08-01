package com.ian.app.muviepedia.di.component

import com.ian.app.muviepedia.di.module.ViewHelperModule
import com.ian.app.muviepedia.feature.detail.DetailFragment
import com.ian.app.muviepedia.feature.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewHelperModule::class])
interface FragmentComponent {

    fun injectInto(fragment: HomeFragment)

    fun injectInto(fragment: DetailFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }
}
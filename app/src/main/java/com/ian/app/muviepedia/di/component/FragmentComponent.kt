package com.ian.app.muviepedia.di.component

import com.ian.app.muviepedia.feature.home.HomeFragment
import dagger.Subcomponent

@Subcomponent
interface FragmentComponent {

    fun injectInto(fragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }
}
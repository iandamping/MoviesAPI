package com.ian.app.muviepedia

import android.app.Application
import com.ian.app.muviepedia.di.component.ApplicationComponent
import com.ian.app.muviepedia.di.component.ApplicationComponentProvider
import com.ian.app.muviepedia.di.component.DaggerApplicationComponent

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
class MoviesApp : Application(), ApplicationComponentProvider {

    override fun provideApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.factory().injectApplication(this)
    }
}

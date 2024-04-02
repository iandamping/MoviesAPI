package com.ian.app.muviepedia.di.module.context

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.ian.app.muviepedia.di.qualifier.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule {

    @Provides
    @ActivityContext
    fun provideContext(activity: FragmentActivity): Context {
        return activity
    }
}

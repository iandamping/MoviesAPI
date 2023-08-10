package com.ian.app.muviepedia.di.module

import android.app.Application
import android.content.Context
import com.ian.app.muviepedia.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule {

    @Provides
    @ApplicationContext
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

}
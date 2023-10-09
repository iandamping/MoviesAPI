package com.ian.app.muviepedia.di.module

import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.ian.app.muviepedia.di.qualifier.ActivityInflater
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @Provides
    @ActivityInflater
    fun provideLayoutInflater(activity: FragmentActivity): LayoutInflater =
        LayoutInflater.from(activity)

    @Provides
    @ActivityInflater
    fun provideFragmentManager(activity: FragmentActivity): FragmentManager =
        activity.supportFragmentManager
}

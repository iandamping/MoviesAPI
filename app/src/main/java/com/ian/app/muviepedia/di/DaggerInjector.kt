package com.ian.app.muviepedia.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ian.app.muviepedia.MoviesApp
import com.ian.app.muviepedia.di.component.FragmentComponent

//
fun FragmentActivity.activityComponent() =
    (application as MoviesApp)
        .provideApplicationComponent()
        .provideActivityComponentFactory()
        .injectActivity(this)

fun Fragment.fragmentComponent(): FragmentComponent =
    requireActivity().activityComponent()
        .provideFragmentComponent()
        .create()

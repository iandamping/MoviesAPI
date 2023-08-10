package com.ian.app.muviepedia.di.module

import androidx.lifecycle.ViewModelProvider
import com.ian.app.muviepedia.di.module.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}
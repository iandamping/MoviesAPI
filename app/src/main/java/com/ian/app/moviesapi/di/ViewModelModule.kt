package com.ian.app.moviesapi.di

import com.ian.app.moviesapi.data.viewmodel.GetHomeMovieViewModel
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
object ViewModelModule {
    val allVmModule = module {
        factory { GetHomeMovieViewModel(get()) }
    }
}
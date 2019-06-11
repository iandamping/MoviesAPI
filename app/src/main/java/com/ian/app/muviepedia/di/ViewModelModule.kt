package com.ian.app.muviepedia.di

import com.ian.app.muviepedia.data.paging.GetPagingDataViewModel
import com.ian.app.muviepedia.data.viewmodel.GetDetalMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.GetHomeMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.GetLocalDataViewModel
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
object ViewModelModule {
    val allVmModule = module {
        factory { GetHomeMovieViewModel(get()) }
        factory { GetPagingDataViewModel(get()) }
        factory { GetDetalMovieViewModel(get()) }
        factory { GetLocalDataViewModel(get()) }
    }
}
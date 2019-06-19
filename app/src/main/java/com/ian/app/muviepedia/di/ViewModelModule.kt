package com.ian.app.muviepedia.di

import com.ian.app.muviepedia.data.paging.movie.GetMoviePagingDataViewModel
import com.ian.app.muviepedia.data.paging.tv.GetTvPagingDataViewModel
import com.ian.app.muviepedia.data.viewmodel.movie.GetDetailMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.movie.GetHomeMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.movie.GetLocalDataViewModel
import com.ian.app.muviepedia.data.viewmodel.tv.GetDetailTvViewModel
import com.ian.app.muviepedia.data.viewmodel.tv.GetHomeTvViewModel
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
object ViewModelModule {
    val allVmModule = module {
        factory { GetHomeMovieViewModel(get()) }
        factory { GetMoviePagingDataViewModel(get()) }
        factory { GetDetailMovieViewModel(get()) }
        factory { GetLocalDataViewModel(get()) }
        factory { GetHomeTvViewModel(get()) }
        factory { GetDetailTvViewModel(get()) }
        factory { GetTvPagingDataViewModel(get()) }
    }
}
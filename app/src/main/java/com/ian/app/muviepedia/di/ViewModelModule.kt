package com.ian.app.muviepedia.di

import com.ian.app.muviepedia.data.paging.movie.GetMoviePagingDataViewModel
import com.ian.app.muviepedia.data.paging.tv.GetTvPagingDataViewModel
import com.ian.app.muviepedia.data.viewmodel.movie.GetDetailMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.movie.GetHomeMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.movie.GetLocalDataViewModel
import com.ian.app.muviepedia.data.viewmodel.tv.GetDetailTvViewModel
import com.ian.app.muviepedia.data.viewmodel.tv.GetHomeTvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
val allVmModule = module {
    viewModel { GetHomeMovieViewModel(get()) }
    viewModel { GetMoviePagingDataViewModel(get()) }
    viewModel { GetDetailMovieViewModel(get()) }
    viewModel { GetLocalDataViewModel(get()) }
    viewModel { GetHomeTvViewModel(get()) }
    viewModel { GetDetailTvViewModel(get()) }
    viewModel { GetTvPagingDataViewModel(get()) }
}

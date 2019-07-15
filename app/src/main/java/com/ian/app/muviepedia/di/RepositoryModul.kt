package com.ian.app.muviepedia.di

import com.ian.app.muviepedia.data.repo.movie.MovieRepository
import com.ian.app.muviepedia.data.repo.tv.TvRepository
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 10/07/2019.
Github = https://github.com/iandamping
 */
val repositoryModule = module {
    single { MovieRepository(get()) }
    single { TvRepository(get()) }
}
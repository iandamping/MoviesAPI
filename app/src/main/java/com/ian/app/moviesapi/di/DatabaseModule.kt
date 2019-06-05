package com.ian.app.moviesapi.di

import androidx.room.Room
import com.ian.app.moviesapi.data.local_data.MovieDatabase
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 05/06/2019.
Github = https://github.com/iandamping
 */
object DatabaseModule {
    val databaseModule = module {
        // Room Database instance
        single { Room.databaseBuilder(get(), MovieDatabase::class.java, "LocalMovieDatabase").build() }
        // localDao instance (get instance from MovieDatabase)
        single { get<MovieDatabase>().movieDao() }
    }
}
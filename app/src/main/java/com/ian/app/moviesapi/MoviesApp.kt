package com.ian.app.moviesapi

import android.app.Application
import com.google.gson.Gson
import com.ian.app.moviesapi.di.DatabaseModule.databaseModule
import com.ian.app.moviesapi.di.NetworkModule.networkMod
import com.ian.app.moviesapi.di.ViewModelModule.allVmModule
import org.koin.android.ext.android.startKoin

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
class MoviesApp : Application() {
    companion object {
        val gson: Gson = Gson()
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkMod, allVmModule, databaseModule))
    }
}
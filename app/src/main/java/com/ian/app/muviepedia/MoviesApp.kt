package com.ian.app.muviepedia

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.ian.app.muviepedia.di.*
import com.ian.app.muviepedia.util.PreferenceHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
class MoviesApp : Application() {
    companion object {
        val gson: Gson = Gson()
        lateinit var prefHelper: PreferenceHelper
        lateinit var mFirebaseAuth: FirebaseAuth
    }

    override fun onCreate() {
        super.onCreate()
        mFirebaseAuth = FirebaseAuth.getInstance()
        prefHelper = PreferenceHelper(this)
        startKoin {
            androidContext(this@MoviesApp)
            modules(listOf(networkMod, databaseModule, allVmModule,detailTvPresenter,detailMoviePresenter))
        }
    }
}
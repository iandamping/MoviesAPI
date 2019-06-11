package com.ian.app.muviepedia

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.ian.app.muviepedia.di.DatabaseModule.databaseModule
import com.ian.app.muviepedia.di.NetworkModule.networkMod
import com.ian.app.muviepedia.di.ViewModelModule.allVmModule
import com.ian.app.muviepedia.util.PreferenceHelper
import org.koin.android.ext.android.startKoin

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
        startKoin(this, listOf(networkMod, allVmModule, databaseModule))
    }
}
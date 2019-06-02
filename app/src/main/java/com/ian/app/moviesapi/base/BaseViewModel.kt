package com.ian.app.moviesapi.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
abstract class BaseViewModel : ViewModel() {
    val liveDataState: MutableLiveData<BaseState> = MutableLiveData()
    protected var fetchingJob = Job()
    protected var uiScope = CoroutineScope(Dispatchers.Main + fetchingJob)
}
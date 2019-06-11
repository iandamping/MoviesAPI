package com.ian.app.muviepedia.data.viewmodel

import com.ian.app.helper.util.doSomethingWithIOScope
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.base.OnGetLocalData
import com.ian.app.muviepedia.data.local_data.LocalMovieData
import com.ian.app.muviepedia.data.local_data.MovieDatabase
import kotlinx.coroutines.cancelChildren

/**
 *
Created by Ian Damping on 05/06/2019.
Github = https://github.com/iandamping
 */
class GetLocalDataViewModel(private val db: MovieDatabase) : BaseViewModel() {

    fun insertLocalData(data: LocalMovieData) {
        uiScope.doSomethingWithIOScope {
            db.movieDao().insertMovieData(data)
        }
    }

    fun updateLocalData(data: LocalMovieData) {
        uiScope.doSomethingWithIOScope {
            db.movieDao().updateMovieData(data)
        }
    }

    fun deleteAllLocalData() {
        uiScope.doSomethingWithIOScope {
            db.movieDao().deleteAllData()
        }
    }

    fun deleteSelectedLocalData(movieID: Int) {
        uiScope.doSomethingWithIOScope {
            db.movieDao().deleteSelectedId(movieID)
        }
    }

    fun getLocalData() {
        liveDataState.value = OnGetLocalData(db.movieDao().loadAllMovieData())
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancelChildren()
    }
}
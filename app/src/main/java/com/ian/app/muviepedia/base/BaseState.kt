package com.ian.app.muviepedia.base

import androidx.lifecycle.LiveData
import com.ian.app.muviepedia.data.local_data.LocalMovieData
import com.ian.app.muviepedia.data.model.MovieData

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
sealed class BaseState

data class OnGetData<out T>(val data: T?) : BaseState()
data class OnGetLocalData(val data: LiveData<List<LocalMovieData>>) : BaseState()
data class OnGetHomeMoviesData(val data: Pair<Pair<List<MovieData>, List<MovieData>>, Pair<List<MovieData>, List<MovieData>>>) :
        BaseState()

data class OnSuccessGetData(val show: Boolean) : BaseState()
data class OnFailedGetData(val msg: String?) : BaseState()
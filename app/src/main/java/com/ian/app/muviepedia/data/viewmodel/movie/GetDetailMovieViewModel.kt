package com.ian.app.muviepedia.data.viewmodel.movie

import com.ian.app.helper.util.deferredPair
import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.base.OnFailedGetData
import com.ian.app.muviepedia.base.OnGetData
import com.ian.app.muviepedia.base.OnSuccessGetData
import com.ian.app.muviepedia.di.NetworkModule.api_key
import kotlinx.coroutines.cancelChildren

/**
 *
Created by Ian Damping on 04/06/2019.
Github = https://github.com/iandamping
 */
class GetDetailMovieViewModel(private val api: ApiInterface) : BaseViewModel() {

    fun getData(movieId: Int) {
        liveDataState.value = OnSuccessGetData(false)
        uiScope.deferredPair(Pair(api.getDetailMovieAsync(movieId, api_key), api.getSimilarMovieAsync(movieId, api_key)), { first, second ->
            liveDataState.value = OnSuccessGetData(true)
            liveDataState.value = OnGetData(Pair(first, second.results))
        }, {
            liveDataState.value = OnSuccessGetData(true)
            liveDataState.value = OnFailedGetData(it)
        })
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancelChildren()
    }
}
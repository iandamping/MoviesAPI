package com.ian.app.moviesapi.data.viewmodel

import com.ian.app.helper.util.combinePairWithPairDeferred
import com.ian.app.moviesapi.api.ApiInterface
import com.ian.app.moviesapi.base.BaseViewModel
import com.ian.app.moviesapi.base.OnFailedGetData
import com.ian.app.moviesapi.base.OnGetHomeMoviesData
import com.ian.app.moviesapi.di.NetworkModule.api_key

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
class GetHomeMovieViewModel(private val api: ApiInterface) : BaseViewModel() {

    fun getMovies() {
        uiScope.combinePairWithPairDeferred(Pair(
                Pair(api.getPopularMovieAsync(api_key), api.getNowPlayingMovieAsync(api_key)),
                Pair(api.getTopRatedMovieAsync(api_key), api.getUpComingMovieAsync(api_key))
        ), {
            liveDataState.value = OnGetHomeMoviesData(
                    Pair(
                            Pair(it.first.first.results, it.first.second.results),
                            Pair(it.second.first.results, it.second.second.results)
                    )
            )
        }, {
            liveDataState.value = OnFailedGetData(it)
        })
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}
package com.ian.app.muviepedia.data.viewmodel.movie

import com.ian.app.helper.util.combinePairWithPairDeferred
import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.base.OnFailedGetData
import com.ian.app.muviepedia.base.OnGetHomeMoviesData
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.di.NetworkModule.api_key

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
class GetHomeMovieViewModel(private val api: ApiInterface) : BaseViewModel() {
    private val ranges = 1..10

    fun getMovies() {
        uiScope.combinePairWithPairDeferred(Pair(
                Pair(api.getPopularMovieAsync(api_key), api.getNowPlayingMovieAsync(api_key)),
                Pair(api.getTopRatedMovieAsync(api_key), api.getUpComingMovieAsync(api_key))
        ), {
            liveDataState.value = OnGetHomeMoviesData(
                    Pair(
                            Pair(popularMovieMapper(it.first.first.results), nowPlayingMovieMapper(it.first.second.results)),
                            Pair(topRatedMovieMapper(it.second.first.results), upComingMovieMapper(it.second.second.results))
                    )
            )
        }, {
            liveDataState.value = OnFailedGetData(it)
        })
    }

    private fun nowPlayingMovieMapper(data: List<MovieData>?): List<MovieData> {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun popularMovieMapper(data: List<MovieData>?): List<MovieData> {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun topRatedMovieMapper(data: List<MovieData>?): List<MovieData> {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun upComingMovieMapper(data: List<MovieData>?): List<MovieData> {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}
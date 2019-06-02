package com.ian.app.moviesapi.ui.activity.home

import androidx.lifecycle.Observer
import com.ian.app.moviesapi.base.BasePresenter
import com.ian.app.moviesapi.base.BaseState
import com.ian.app.moviesapi.base.OnFailedGetData
import com.ian.app.moviesapi.base.OnGetHomeMoviesData
import com.ian.app.moviesapi.data.model.MovieData
import com.ian.app.moviesapi.data.viewmodel.GetHomeMovieViewModel

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
class HomePresenter(private val vm: GetHomeMovieViewModel) : BasePresenter<HomeView>() {
    private val ranges = 1..10
    override fun onCreate() {
        view()?.initView()
        getData()
    }

    private fun getData() {
        vm.getMovies().apply {
            vm.liveDataState.observe(getLifeCycleOwner(), Observer { extractData(it) })
        }

    }

    private fun extractData(data: BaseState) {
        when (data) {
            is OnFailedGetData -> view()?.onFailGetData(data.msg)
            is OnGetHomeMoviesData -> {
                popularMovieMapper(data.data.first.first)
                nowPlayingMovieMapper(data.data.first.second)
                topRatedMovieMapper(data.data.second.first)
                upComingMovieMapper(data.data.second.second)
            }
        }
    }

    private fun popularMovieMapper(data: List<MovieData>?) {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        view()?.onSuccessGetPopularMovie(newData)
    }

    private fun nowPlayingMovieMapper(data: List<MovieData>?) {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        view()?.onSuccessGetNowPlayingMovie(newData)
    }

    private fun topRatedMovieMapper(data: List<MovieData>?) {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        view()?.onSuccessGetTopRatedMovie(newData)
    }

    private fun upComingMovieMapper(data: List<MovieData>?) {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        view()?.onSuccessGetUpComingMovie(newData)
    }

}
package com.ian.app.moviesapi.ui.activity.discover

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ian.app.moviesapi.base.BasePresenter
import com.ian.app.moviesapi.base.BaseState
import com.ian.app.moviesapi.base.OnFailedGetData
import com.ian.app.moviesapi.base.OnGetData
import com.ian.app.moviesapi.data.model.MovieData
import com.ian.app.moviesapi.data.paging.popular_movie.GetPopularMoviePagingViewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DiscoverPresenter(private val vm: GetPopularMoviePagingViewModel) : BasePresenter<DiscoverView>() {
    override fun onCreate() {
        view()?.initView()
        getDiscoverData()
    }

    private fun getDiscoverData() {
        vm.getAllMovies().observe(getLifeCycleOwner(), Observer {
            view()?.onSuccessGetData(it)
        })
    }
}
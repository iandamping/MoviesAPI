package com.ian.app.moviesapi.ui.activity.discover

import androidx.lifecycle.Observer
import com.ian.app.moviesapi.base.BasePresenter
import com.ian.app.moviesapi.data.paging.GetPagingDataViewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DiscoverPresenter(private val vm: GetPagingDataViewModel) : BasePresenter<DiscoverView>() {
    override fun onCreate() {
        view()?.initView()
    }

    fun getDiscoverData(states: String?) {
        if (states != null) {
            vm.getAllMovies(states).observe(getLifeCycleOwner(), Observer {
                view()?.onSuccessGetData(it)
            })
        }
    }
}
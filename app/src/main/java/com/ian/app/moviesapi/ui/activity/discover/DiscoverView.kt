package com.ian.app.moviesapi.ui.activity.discover

import androidx.paging.PagedList
import com.ian.app.moviesapi.base.BaseView
import com.ian.app.moviesapi.data.model.MovieData

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
interface DiscoverView : BaseView {
    fun onSuccessGetData(data: PagedList<MovieData>?)
    fun onFailGetData(msg: String?)
}
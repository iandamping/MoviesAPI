package com.ian.app.moviesapi.ui.activity.detail

import com.ian.app.moviesapi.base.BaseView
import com.ian.app.moviesapi.data.local_data.LocalMovieData
import com.ian.app.moviesapi.data.model.DetailMovieData
import com.ian.app.moviesapi.data.model.MovieData

/**
 *
Created by Ian Damping on 04/06/2019.
Github = https://github.com/iandamping
 */
interface DetailView : BaseView {
    fun onSuccessGetData(data: Pair<DetailMovieData?, List<MovieData>>)
    fun onSuccessGetLocalData(data: List<LocalMovieData>)
    fun onFailedGetData(msg: String?)
}
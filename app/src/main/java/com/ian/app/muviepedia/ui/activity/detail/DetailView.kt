package com.ian.app.muviepedia.ui.activity.detail

import com.ian.app.muviepedia.base.BaseView
import com.ian.app.muviepedia.data.local_data.LocalMovieData
import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.data.model.MovieData

/**
 *
Created by Ian Damping on 04/06/2019.
Github = https://github.com/iandamping
 */
interface DetailView : BaseView {
    fun onSuccessGetData(data: Pair<DetailMovieData?, List<MovieData>>)
    fun onSuccessGetLocalData(data: List<LocalMovieData>)
    fun isAlreadyLoggedin(data: Boolean)
}
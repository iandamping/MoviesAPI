package com.ian.app.moviesapi.ui.activity.home

import com.ian.app.moviesapi.base.BaseView
import com.ian.app.moviesapi.data.model.MovieData

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
interface HomeView : BaseView {
    fun onSuccessGetPopularMovie(data: List<MovieData>?)
    fun onSuccessGetNowPlayingMovie(data: List<MovieData>?)
    fun onSuccessGetTopRatedMovie(data: List<MovieData>?)
    fun onSuccessGetUpComingMovie(data: List<MovieData>?)
    //    fun onSuccessGetLatestMovie(data: List<MovieData>?)
    fun onFailGetData(msg: String?)
}
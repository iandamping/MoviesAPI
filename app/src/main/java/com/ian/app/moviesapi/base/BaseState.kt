package com.ian.app.moviesapi.base

import com.ian.app.moviesapi.data.model.DetailMovieData
import com.ian.app.moviesapi.data.model.MovieData

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
sealed class BaseState

data class OnGetData<T>(val data: T?) : BaseState()
data class OnGetDetailMovieData(val data: DetailMovieData) : BaseState()
data class OnGetSimilarMovieData(val data: List<MovieData>) : BaseState()
data class OnGetHomeMoviesData(val data: Pair<Pair<List<MovieData>, List<MovieData>>, Pair<List<MovieData>, List<MovieData>>>) :
        BaseState()

data class OnSuccessGetData(val show: Boolean) : BaseState()
data class OnFailedGetData(val msg: String?) : BaseState()
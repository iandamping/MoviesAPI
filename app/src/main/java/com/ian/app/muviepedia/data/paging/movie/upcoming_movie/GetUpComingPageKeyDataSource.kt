package com.ian.app.muviepedia.data.paging.movie.upcoming_movie

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.doSomethingWithDeferred
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.BuildConfig
import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.di.NetworkModule
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 03/06/2019.
Github = https://github.com/iandamping
 */
class GetUpComingPageKeyDataSource(private val api: ApiInterface, private val scope: CoroutineScope) :
        PageKeyedDataSource<Int, MovieData>() {
    private val page = 1
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieData>) {
        scope.doSomethingWithDeferred(api.pagingGetUpComingMovieAsync(NetworkModule.api_key, page), {
            callback.onResult(it.results, null, page + 1)
        }, {
            if (BuildConfig.DEBUG) logE(it)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieData>) {
        scope.doSomethingWithDeferred(api.pagingGetUpComingMovieAsync(NetworkModule.api_key, params.key), {
            callback.onResult(it.results, params.key + 1)
        }, {
            if (BuildConfig.DEBUG) logE(it)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieData>) {
        val adjacentKey = if (params.key > 1) params.key - 1 else null
        scope.doSomethingWithDeferred(api.pagingGetUpComingMovieAsync(NetworkModule.api_key, params.key), {
            callback.onResult(it.results, adjacentKey)
        }, {
            if (BuildConfig.DEBUG) logE(it)
        })
    }
}
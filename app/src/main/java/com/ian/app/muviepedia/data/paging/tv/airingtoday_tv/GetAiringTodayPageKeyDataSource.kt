package com.ian.app.muviepedia.data.paging.tv.airingtoday_tv

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.doSomethingWithDeferred
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.BuildConfig
import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.util.MovieConstant.api_key
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class GetAiringTodayPageKeyDataSource(private val api: ApiInterface, private val scope: CoroutineScope) :
        PageKeyedDataSource<Int, TvData>() {
    private val page = 1
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, TvData>) {
        scope.doSomethingWithDeferred(api.pagingGetAiringTodayTvAsync(api_key, page), {
            callback.onResult(it.results, null, page + 1)
        }, {
            if (BuildConfig.DEBUG) logE(it)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvData>) {
        scope.doSomethingWithDeferred(api.pagingGetAiringTodayTvAsync(api_key, params.key), {
            callback.onResult(it.results, params.key + 1)
        }, {
            if (BuildConfig.DEBUG) logE(it)

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvData>) {
        val adjacentKey = if (params.key > 1) params.key - 1 else null
        scope.doSomethingWithDeferred(api.pagingGetAiringTodayTvAsync(api_key, params.key), {
            callback.onResult(it.results, adjacentKey)
        }, {
            if (BuildConfig.DEBUG) logE(it)

        })
    }
}
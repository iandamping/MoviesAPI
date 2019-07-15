package com.ian.app.muviepedia.data.paging.tv.toprated_tv

import androidx.paging.PageKeyedDataSource
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.data.repo.tv.TvRepository
import com.ian.app.muviepedia.util.extractDeferred
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class GetTopRatedTvPageKeyDataSource(private val repo: TvRepository, private val scope: CoroutineScope) :
        PageKeyedDataSource<Int, TvData>() {
    private val page = 1
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, TvData>) {
        scope.extractDeferred {
            repo.getTopRatedPagingAsync(page).apply {
                callback.onResult(this.await().results, null, page + 1)
            }
        }

        /*scope.doSomethingWithDeferred(api.pagingGetTopRatedTvAsync(api_key, page), {
            callback.onResult(it.results, null, page + 1)
        }, {
            if (BuildConfig.DEBUG) logE(it)
        })*/
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvData>) {
        scope.extractDeferred {
            repo.getTopRatedPagingAsync(params.key).apply {
                callback.onResult(this.await().results, params.key + 1)
            }
        }

        /* scope.doSomethingWithDeferred(api.pagingGetTopRatedTvAsync(api_key, params.key), {
             callback.onResult(it.results, params.key + 1)
         }, {
             if (BuildConfig.DEBUG) logE(it)

         })*/
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvData>) {
        val adjacentKey = if (params.key > 1) params.key - 1 else null
        scope.extractDeferred {
            repo.getTopRatedPagingAsync(params.key).apply {
                callback.onResult(this.await().results, adjacentKey)
            }
        }

        /* scope.doSomethingWithDeferred(api.pagingGetTopRatedTvAsync(api_key, params.key), {
             callback.onResult(it.results, adjacentKey)
         }, {
             if (BuildConfig.DEBUG) logE(it)

         })*/
    }
}
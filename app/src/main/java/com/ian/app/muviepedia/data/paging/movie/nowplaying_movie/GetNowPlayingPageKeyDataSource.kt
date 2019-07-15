package com.ian.app.muviepedia.data.paging.movie.nowplaying_movie

import androidx.paging.PageKeyedDataSource
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.repo.movie.MovieRepository
import com.ian.app.muviepedia.util.extractDeferred
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class GetNowPlayingPageKeyDataSource(private val repo: MovieRepository, private val scope: CoroutineScope) : PageKeyedDataSource<Int, MovieData>() {
    private val page = 1
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieData>) {
        scope.extractDeferred {

            repo.getNowPlayingMoviePagingAsync(page).apply {
                callback.onResult(this.await().results, null, page + 1)
            }
        }
        /*scope.doSomethingWithDeferred(api.pagingGetNowPlayingMovieMovieAsync(api_key, page), {
            callback.onResult(it.results, null, page + 1)
        }, {
            if (BuildConfig.DEBUG) logE(it)
        })*/
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieData>) {
        scope.extractDeferred {
            repo.getNowPlayingMoviePagingAsync(params.key).apply {
                callback.onResult(this.await().results, params.key + 1)
            }
        }


        /* scope.doSomethingWithDeferred(api.pagingGetNowPlayingMovieMovieAsync(api_key, params.key), {
             callback.onResult(it.results, params.key + 1)
         }, {
             if (BuildConfig.DEBUG) logE(it)
         })*/
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieData>) {
        val adjacentKey = if (params.key > 1) params.key - 1 else null
        scope.extractDeferred {
            repo.getNowPlayingMoviePagingAsync(params.key).apply {
                callback.onResult(this.await().results, adjacentKey)
            }
        }
        /* scope.doSomethingWithDeferred(api.pagingGetNowPlayingMovieMovieAsync(api_key, params.key), {
             callback.onResult(it.results, adjacentKey)
         }, {
             if (BuildConfig.DEBUG) logE(it)
         })*/
    }
}
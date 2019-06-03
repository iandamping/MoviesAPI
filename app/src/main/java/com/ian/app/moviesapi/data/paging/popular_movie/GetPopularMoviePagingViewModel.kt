package com.ian.app.moviesapi.data.paging.popular_movie

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.app.moviesapi.api.ApiInterface
import com.ian.app.moviesapi.base.BaseViewModel
import com.ian.app.moviesapi.data.model.MovieData

/**
 *
Created by Ian Damping on 03/06/2019.
Github = https://github.com/iandamping
 */
class GetPopularMoviePagingViewModel(private val api: ApiInterface) : BaseViewModel() {
    private val pageSize = 10
    private val isPlaceHolder = true
    private lateinit var movieList: LiveData<PagedList<MovieData>>

    fun getAllMovies(): LiveData<PagedList<MovieData>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(isPlaceHolder)
            .setInitialLoadSizeHint(pageSize)
            .setPageSize(15)
            .build()
        movieList = initPagedListBuilder(config).build()
        return movieList
    }

    private fun initPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, MovieData> {
        val dataSourceFactory = object : DataSource.Factory<Int, MovieData>() {
            override fun create(): DataSource<Int, MovieData> {
                return GetPopularPageKeyDataSource(api, uiScope)
            }
        }
        return LivePagedListBuilder<Int, MovieData>(dataSourceFactory, config)
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}
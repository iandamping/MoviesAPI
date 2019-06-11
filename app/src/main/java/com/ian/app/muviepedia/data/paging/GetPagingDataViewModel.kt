package com.ian.app.muviepedia.data.paging

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.paging.popular_movie.GetPopularPageKeyDataSource
import com.ian.app.muviepedia.data.paging.toprated_movie.GetTopRatedPageKeyDataSource
import com.ian.app.muviepedia.data.paging.upcoming_movie.GetUpComingPageKeyDataSource
import com.ian.app.muviepedia.util.MovieConstant.popularPagingState
import com.ian.app.muviepedia.util.MovieConstant.topRatedPagingState

/**
 *
Created by Ian Damping on 03/06/2019.
Github = https://github.com/iandamping
 */
class GetPagingDataViewModel(private val api: ApiInterface) : BaseViewModel() {
    private val pageSize = 10
    private val isPlaceHolder = true
    private lateinit var movieList: LiveData<PagedList<MovieData>>

    fun getAllMovies(states: String): LiveData<PagedList<MovieData>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(isPlaceHolder)
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(15)
                .build()
        movieList = initPagedListBuilder(config, states).build()
        return movieList
    }

    private fun initPagedListBuilder(config: PagedList.Config, states: String): LivePagedListBuilder<Int, MovieData> {
        val dataSourceFactory = object : DataSource.Factory<Int, MovieData>() {
            override fun create(): DataSource<Int, MovieData> {
                return when (states) {
                    popularPagingState -> GetPopularPageKeyDataSource(
                            api,
                            uiScope
                    )
                    topRatedPagingState -> GetTopRatedPageKeyDataSource(api, uiScope)
                    else -> GetUpComingPageKeyDataSource(api, uiScope)
                }

            }
        }
        return LivePagedListBuilder<Int, MovieData>(dataSourceFactory, config)
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}
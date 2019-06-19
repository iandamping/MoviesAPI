package com.ian.app.muviepedia.data.paging.tv

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.data.paging.tv.airingtoday_tv.GetAiringTodayPageKeyDataSource
import com.ian.app.muviepedia.data.paging.tv.onair_tv.GetOnAirTvPageKeyDataSource
import com.ian.app.muviepedia.data.paging.tv.popular_tv.GetPopularTvPageKeyDataSource
import com.ian.app.muviepedia.util.MovieConstant.airingTodayPagingState
import com.ian.app.muviepedia.util.MovieConstant.onAirPagingState
import com.ian.app.muviepedia.util.MovieConstant.popularPagingState

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class GetTvPagingDataViewModel(private val api: ApiInterface) : BaseViewModel() {
    private val pageSize = 10
    private val isPlaceHolder = true
    private lateinit var tvList: LiveData<PagedList<TvData>>

    fun getAllTvs(states: String): LiveData<PagedList<TvData>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(isPlaceHolder)
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(15)
                .build()
        tvList = initPagedListBuilder(config, states).build()
        return tvList
    }

    private fun initPagedListBuilder(config: PagedList.Config, states: String): LivePagedListBuilder<Int, TvData> {
        val dataSourceFactory = object : DataSource.Factory<Int, TvData>() {
            override fun create(): DataSource<Int, TvData> {
                return when (states) {
                    airingTodayPagingState -> GetAiringTodayPageKeyDataSource(api, uiScope)
                    onAirPagingState -> GetOnAirTvPageKeyDataSource(api, uiScope)
                    popularPagingState -> GetPopularTvPageKeyDataSource(api, uiScope)
                    else -> GetPopularTvPageKeyDataSource(api, uiScope)
                }

            }
        }
        return LivePagedListBuilder<Int, TvData>(dataSourceFactory, config)
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}
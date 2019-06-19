package com.ian.app.muviepedia.data.viewmodel.tv

import com.ian.app.helper.util.combinePairWithPairDeferred
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.base.OnFailedGetData
import com.ian.app.muviepedia.base.OnGetHomeTvData
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.di.NetworkModule.api_key

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class GetHomeTvViewModel(private val api: ApiInterface) : BaseViewModel() {
    private val ranges = 1..10

    fun getTv() {
        uiScope.combinePairWithPairDeferred(Pair(
                Pair(api.getPopularTvAsync(api_key), api.getTopRatedTvAsync(api_key))
                , Pair(api.getAiringTodayTvAsync(api_key), api.getOnAirTvAsync(api_key))
        ), {
            logE(it.first.first.results.size.toString() + " ini asd size")
            logE(it.first.second.results.size.toString() + " ini ffs size")
            logE(it.second.first.results.size.toString() + " ini as size")
            logE(it.second.second.results.size.toString() + " ini asda size")
            liveDataState.value = OnGetHomeTvData(
                    Pair(
                            Pair(popularTvMapper(it.first.first.results), topRatedTvMapper(it.first.second.results)),
                            Pair(airingTodayTvMapper(it.second.first.results), onAirTvMapper(it.second.second.results))
                    )
            )
        }, {
            liveDataState.value = OnFailedGetData(it)
        })
    }

    private fun topRatedTvMapper(data: List<TvData>?): List<TvData> {
        val newData: MutableList<TvData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun popularTvMapper(data: List<TvData>?): List<TvData> {
        val newData: MutableList<TvData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun airingTodayTvMapper(data: List<TvData>?): List<TvData> {
        val newData: MutableList<TvData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun onAirTvMapper(data: List<TvData>?): List<TvData> {
        logE(data?.size.toString() + " ini size macem2")
        val newData: MutableList<TvData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }
}
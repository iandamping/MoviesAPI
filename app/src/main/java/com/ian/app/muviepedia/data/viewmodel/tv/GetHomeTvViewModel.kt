package com.ian.app.muviepedia.data.viewmodel.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ian.app.helper.util.computeQuadResult
import com.ian.app.helper.util.retryIO
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.data.repo.tv.TvRepository
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class GetHomeTvViewModel(private val repo: TvRepository) : BaseViewModel() {
    private val ranges = 1..10

    private val _allHomeTv: MutableLiveData<Pair<Pair<List<TvData>, List<TvData>>, Pair<List<TvData>, List<TvData>>>> = MutableLiveData()

    val allHomeMovie: LiveData<Pair<Pair<List<TvData>, List<TvData>>, Pair<List<TvData>, List<TvData>>>>
        get() = _allHomeTv

    init {
        viewModelScope.launch {
            retryIO { getTv() }
        }

    }

    private suspend fun getTv() {
        val work1 = repo.getPopularTvAsync()
        val work2 = repo.getTopRatedTvAsync()
        val work3 = repo.getAiringTodayTvAsync()
        val work4 = repo.getOnAirTvAsync()
        _allHomeTv.value = computeQuadResult(popularTvMapper(work1.await().results), topRatedTvMapper(work2.await().results),
                airingTodayTvMapper(work3.await().results), onAirTvMapper(work4.await().results))
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
        val newData: MutableList<TvData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }
}
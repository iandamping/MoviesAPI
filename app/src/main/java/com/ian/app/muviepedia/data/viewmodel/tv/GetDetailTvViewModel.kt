package com.ian.app.muviepedia.data.viewmodel.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ian.app.helper.util.computeDoubleResult
import com.ian.app.helper.util.retryIO
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.data.model.DetailTvData
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.data.repo.tv.TvRepository
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class GetDetailTvViewModel(private val repo: TvRepository) : BaseViewModel() {

    private val _detailTv: MutableLiveData<Pair<DetailTvData, List<TvData>>> = MutableLiveData()

    val detailTv: LiveData<Pair<DetailTvData, List<TvData>>>
        get() = _detailTv

    fun getData(tvID: Int) {
        viewModelScope.launch {
            retryIO { extractAllData(tvID) }

        }

    }

    private suspend fun extractAllData(tvId: Int) {
        val work1 = repo.getDetailTvAsync(tvId)
        val work2 = repo.getSimilarTvAsync(tvId)
        _detailTv.value = computeDoubleResult(work1.await(), work2.await().results)
    }


}
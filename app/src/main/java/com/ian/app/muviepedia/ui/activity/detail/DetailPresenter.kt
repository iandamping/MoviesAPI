package com.ian.app.muviepedia.ui.activity.detail

import androidx.lifecycle.Observer
import com.ian.app.muviepedia.base.*
import com.ian.app.muviepedia.data.local_data.LocalMovieData
import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.viewmodel.GetDetalMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.GetLocalDataViewModel

/**
 *
Created by Ian Damping on 04/06/2019.
Github = https://github.com/iandamping
 */
class DetailPresenter(private val vm: GetDetalMovieViewModel, private val vmLocal: GetLocalDataViewModel) : BasePresenter<DetailView>() {

    override fun onCreate() {
        view()?.initView()
    }

    fun getData(movieID: Int?) {
        if (movieID != null && movieID != 0) {
            vm.getData(movieID).apply {
                vm.liveDataState.observe(getLifeCycleOwner(), Observer {
                    when (it) {
                        is OnSuccessGetData -> setDialogShow(it.show)
                        is OnGetData<*> -> view()?.onSuccessGetData(it.data as Pair<DetailMovieData?, List<MovieData>>)
                        is OnFailedGetData -> view()?.onFailedGetData(it.msg)
                    }
                })
            }
            getLocalData()
        }
    }

    private fun getLocalData() {
        vmLocal.getLocalData().apply {
            vmLocal.liveDataState.observe(getLifeCycleOwner(), Observer {
                when (it) {
                    is OnGetLocalData -> it.data.observe(getLifeCycleOwner(), Observer { localData ->
                        view()?.onSuccessGetLocalData(localData)
                    })
                }
            })
        }
    }

    fun saveLocalData(data: LocalMovieData?) {
        if (data != null) {
            vmLocal.insertLocalData(data)
        }
    }

    fun deleteLocalID(movieID: Int?) {
        if (movieID != null) vmLocal.deleteSelectedLocalData(movieID)
    }

}
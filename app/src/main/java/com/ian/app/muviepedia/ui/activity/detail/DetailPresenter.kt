package com.ian.app.muviepedia.ui.activity.detail

import androidx.lifecycle.Observer
import com.ian.app.muviepedia.MoviesApp.Companion.prefHelper
import com.ian.app.muviepedia.base.*
import com.ian.app.muviepedia.data.local_data.LocalMovieData
import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.viewmodel.movie.GetDetailMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.movie.GetLocalDataViewModel
import com.ian.app.muviepedia.util.MovieConstant.saveUserProfile

/**
 *
Created by Ian Damping on 04/06/2019.
Github = https://github.com/iandamping
 */
class DetailPresenter(private val vm: GetDetailMovieViewModel, private val vmLocal: GetLocalDataViewModel) : BasePresenter<DetailView>() {

    override fun onCreate() {
        getLoggedinStatus()
        view()?.initView()
    }

    private fun getLoggedinStatus() {
        if (!prefHelper.getStringInSharedPreference(saveUserProfile).isNullOrBlank()) {
            view()?.isAlreadyLoggedin(true)
        } else {
            view()?.isAlreadyLoggedin(false)
        }
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
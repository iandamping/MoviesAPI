package com.ian.app.muviepedia.ui.activity.detail_tv

import androidx.lifecycle.Observer
import com.ian.app.muviepedia.MoviesApp
import com.ian.app.muviepedia.base.BasePresenter
import com.ian.app.muviepedia.base.OnFailedGetData
import com.ian.app.muviepedia.base.OnGetData
import com.ian.app.muviepedia.base.OnSuccessGetData
import com.ian.app.muviepedia.data.model.DetailTvData
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.data.viewmodel.tv.GetDetailTvViewModel
import com.ian.app.muviepedia.util.MovieConstant

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class DetailTvPresenter(private val vm: GetDetailTvViewModel) : BasePresenter<DetailTvView>() {

    override fun onCreate() {
        view()?.initView()
        getLoggedinStatus()
    }

    private fun getLoggedinStatus() {
        if (!MoviesApp.prefHelper.getStringInSharedPreference(MovieConstant.saveUserProfile).isNullOrBlank()) {
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
                        is OnGetData<*> -> view()?.onSuccessGetData(it.data as Pair<DetailTvData?, List<TvData>>)
                        is OnFailedGetData -> view()?.onFailedGetData(it.msg)
                    }
                })
            }
        }
    }
}
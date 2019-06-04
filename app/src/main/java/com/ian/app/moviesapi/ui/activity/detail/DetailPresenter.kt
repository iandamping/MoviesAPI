package com.ian.app.moviesapi.ui.activity.detail

import androidx.lifecycle.Observer
import com.ian.app.moviesapi.base.*
import com.ian.app.moviesapi.data.viewmodel.GetDetalMovieViewModel

/**
 *
Created by Ian Damping on 04/06/2019.
Github = https://github.com/iandamping
 */
class DetailPresenter(private val vm: GetDetalMovieViewModel) : BasePresenter<DetailView>() {

    override fun onCreate() {
        view()?.initView()
    }

    fun getData(movieID: Int?) {
        if (movieID != null && movieID != 0) {
            vm.getData(movieID).apply {
                vm.liveDataState.observe(getLifeCycleOwner(), Observer {
                    when (it) {
                        is OnSuccessGetData -> setDialogShow(it.show)
                        is OnGetSimilarMovieData -> view()?.onSuccessGetSimilarData(it.data)
                        is OnGetDetailMovieData -> view()?.onSuccessGetDetailData(it.data)
                        is OnFailedGetData -> view()?.onFailedGetData(it.msg)
                    }
                })
            }
        }
    }

}
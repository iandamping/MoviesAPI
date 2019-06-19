package com.ian.app.muviepedia.ui.activity.detail_tv

import com.ian.app.muviepedia.base.BaseView
import com.ian.app.muviepedia.data.model.DetailTvData
import com.ian.app.muviepedia.data.model.TvData

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
interface DetailTvView : BaseView {
    fun onSuccessGetData(data: Pair<DetailTvData?, List<TvData>>)
    fun isAlreadyLoggedin(data: Boolean)
}
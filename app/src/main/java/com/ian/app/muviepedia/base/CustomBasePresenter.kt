package com.ian.app.muviepedia.base

import android.view.View

/**
 *
Created by Ian Damping on 06/06/2019.
Github = https://github.com/iandamping
 */
interface CustomBasePresenter {
    fun initView(view: View)
    fun initFetchNetworkData()
    fun initLocalData()
    fun onFailedGetData(msg: String?)
}
package com.ian.app.muviepedia.base

/**
 *
Created by Ian Damping on 06/06/2019.
Github = https://github.com/iandamping
 */
interface CustomBaseActivity {
    fun initView()
    fun initFetchNetworkData()
    fun initLocalData()
    fun onFailedGetData(msg: String?)
}
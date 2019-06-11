package com.ian.app.muviepedia.base

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

interface BaseView {
    fun initView()
    fun onFailedGetData(msg: String?)
}
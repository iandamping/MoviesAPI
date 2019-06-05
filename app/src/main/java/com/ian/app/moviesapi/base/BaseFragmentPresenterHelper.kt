package com.ian.app.moviesapi.base

import android.view.View

interface BaseFragmentPresenterHelper {
    fun onAttach()
    fun onCreateView(view: View)
}
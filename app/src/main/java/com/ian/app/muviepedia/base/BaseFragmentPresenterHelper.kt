package com.ian.app.muviepedia.base

import android.view.View

interface BaseFragmentPresenterHelper {
    fun onAttach()
    fun onCreateView(view: View)
}
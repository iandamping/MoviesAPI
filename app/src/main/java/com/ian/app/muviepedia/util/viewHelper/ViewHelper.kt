package com.ian.app.muviepedia.util.viewHelper

import android.view.View
import android.view.ViewGroup
import android.widget.EditText

interface ViewHelper {

    fun inflates(viewGroup: ViewGroup, layout: Int): View

    fun setMarginProgrammatically(
        view: View,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    )

    fun showKeyboard(view: EditText)

    fun hideKeyboard(view: EditText)
}

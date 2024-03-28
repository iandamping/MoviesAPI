package com.ian.app.muviepedia.util.viewHelper

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.ian.app.muviepedia.di.qualifier.ActivityInflater
import javax.inject.Inject

class ViewHelperImpl @Inject constructor(@ActivityInflater private val layoutInflater: LayoutInflater) :
    ViewHelper {

    override fun inflates(viewGroup: ViewGroup, layout: Int): View {
        return layoutInflater.inflate(layout, viewGroup, false)
    }

    override fun setMarginProgrammatically(
        view: View,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val bottomMarginDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                bottom.toFloat(),
                view.context.resources.displayMetrics
            ).toInt()
            val topMarginDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                top.toFloat(),
                view.context.resources.displayMetrics
            ).toInt()
            val rightMarginDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                right.toFloat(),
                view.context.resources.displayMetrics
            ).toInt()
            val leftMarginDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                left.toFloat(),
                view.context.resources.displayMetrics
            ).toInt()

            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(leftMarginDp, topMarginDp, rightMarginDp, bottomMarginDp)
            view.requestLayout()
        }
    }

    override fun showKeyboard(view: EditText) {
        try {
            view.requestFocus()
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } catch (ignored: RuntimeException) {

        }
    }

    override fun hideKeyboard(view: EditText) {
        try {
            view.clearFocus()
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: RuntimeException) {

        }
    }
}

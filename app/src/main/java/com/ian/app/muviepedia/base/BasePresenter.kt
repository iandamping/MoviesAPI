package com.ian.app.muviepedia.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.ian.app.muviepedia.R

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

abstract class BasePresenter<View> : LifecycleObserver, BasePresenterHelper {
    private var view: View? = null
    private var viewLifecycle: Lifecycle? = null
    private lateinit var lifecycleOwner: FragmentActivity
    private lateinit var dialog: AlertDialog


    fun attachView(view: View, lifeCycleOwner: FragmentActivity) {
        this.view = view
        this.viewLifecycle = lifeCycleOwner.lifecycle
        this.lifecycleOwner = lifeCycleOwner
        setBaseDialog()
        lifeCycleOwner.lifecycle.addObserver(this)
    }

    protected fun view(): View? {
        return view
    }

    protected fun getLifeCycleOwner(): FragmentActivity {
        return lifecycleOwner
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        view = null
        viewLifecycle = null

    }

    private fun setBaseDialog() {
        val dialogBuilder = AlertDialog.Builder(lifecycleOwner)
        val inflater = lifecycleOwner.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_loading, null)

        dialogBuilder.setView(dialogView)
        dialog = dialogBuilder.create()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
    }

    protected fun setDialogShow(status: Boolean) {
        if (status) {
            dialog.dismiss()
        } else {
            dialog.show()
        }
    }
}
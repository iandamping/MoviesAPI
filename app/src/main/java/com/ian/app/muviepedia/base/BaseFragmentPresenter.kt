package com.ian.app.muviepedia.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.ian.app.helper.util.layoutInflater
import com.ian.app.muviepedia.R

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
abstract class BaseFragmentPresenter<View> : LifecycleObserver, BaseFragmentPresenterHelper {
    private var view: View? = null
    private lateinit var lifecycleOwner: Fragment
    private var viewLifecycle: Lifecycle? = null
    private lateinit var dialog: AlertDialog

    fun attachView(view: View, lifeCycleOwner: Fragment) {
        this.view = view
        this.lifecycleOwner = lifeCycleOwner
        setBaseDialog(lifecycleOwner.context)
        lifeCycleOwner.lifecycle.addObserver(this)
    }

    protected fun view(): View? {
        return view
    }

    protected fun getLifeCycleOwner(): Fragment {
        return lifecycleOwner
    }

    private fun setBaseDialog(ctx: Context?) {
        if (ctx != null) {
            val dialogBuilder = AlertDialog.Builder(ctx)
            val inflater = ctx.layoutInflater
            val dialogView = inflater.inflate(R.layout.custom_loading, null)

            dialogBuilder.setView(dialogView)
            dialog = dialogBuilder.create()
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        view = null
        viewLifecycle = null
    }

    protected fun setDialogShow(status: Boolean) {
        if (status) {
            dialog.dismiss()
        } else {
            dialog.show()
        }
    }
}
package com.ian.app.moviesapi.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ian.app.helper.util.checkConnectivityStatus
import com.ian.app.helper.util.layoutInflater
import com.ian.app.helper.util.myToast
import com.ian.app.moviesapi.R

/**
 *
Created by Ian Damping on 06/06/2019.
Github = https://github.com/iandamping
 */
abstract class BaseFragment : Fragment(), CustomBasePresenter {
    private lateinit var dialog: AlertDialog

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkConnectivityStatus {
            when (it) {
                true -> initFetchNetworkData()
                false -> context?.myToast("Periksa Koneksi Internet Anda")
            }
        }
        initLocalData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setBaseDialog(context)
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

    protected fun setDialogShow(status: Boolean) {
        if (status) {
            dialog.dismiss()
        } else {
            dialog.show()
        }
    }
}
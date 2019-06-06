package com.ian.app.moviesapi.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.helper.util.checkConnectivityStatus
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.layoutInflater
import com.ian.app.helper.util.myToast
import com.ian.app.moviesapi.R

/**
 *
Created by Ian Damping on 06/06/2019.
Github = https://github.com/iandamping
 */
abstract class BaseActivity : AppCompatActivity(), CustomBaseActivity {
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setBaseDialog(this)
        initView()
        initLocalData()
        checkConnectivityStatus {
            when (it) {
                true -> initFetchNetworkData()
                false -> myToast("Periksa Koneksi Internet Anda")
            }
        }
    }


    private fun setBaseDialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = context.layoutInflater
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
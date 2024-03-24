package com.ian.app.muviepedia.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import com.ian.app.muviepedia.di.qualifier.ApplicationContext
import javax.inject.Inject

class UtilityHelperImpl @Inject constructor(@ApplicationContext private val context: Context) :
    UtilityHelper {

    override fun getDrawable(resources: Int): Drawable? {
        Log.e("TAG", "getDrawable: aman", )
        return AppCompatResources.getDrawable(context, resources)
    }

    override fun getString(resources: Int): String {
        return context.getString(resources)
    }
}

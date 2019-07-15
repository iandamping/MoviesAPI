package com.ian.app.muviepedia.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.ian.app.helper.util.layoutInflater
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.retryIO
import com.ian.app.muviepedia.R
import kotlinx.android.synthetic.main.custom_delete_item_dialog.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

/**
 *
Created by Ian Damping on 11/06/2019.
Github = https://github.com/iandamping
 */

inline fun Context.alertHelperDelete(imageUrl: String?, crossinline doSomething: () -> Unit) {
    val dialogBuilder = AlertDialog.Builder(this)
    val inflater = this.layoutInflater
    val dialogView = inflater.inflate(R.layout.custom_delete_item_dialog, null)
    dialogView.ivAlertDelete.loadWithGlide(imageUrl)
    dialogBuilder.setPositiveButton("Oke") { dialog, _ ->
        dialog.dismiss()
        doSomething()
    }
    dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
        dialog.dismiss()
    }

    dialogBuilder.setView(dialogView)
    val alert = dialogBuilder.create()
    alert.setCancelable(false)
    alert.show()

}

inline fun <T> CoroutineScope.extractDeferred(crossinline heavyFunction: suspend () -> Deferred<T>) {
    this.launch {
        retryIO { heavyFunction().await() }
    }
}

/*uiScope.launch {
           try {
               val work1 = repo.getPopularMovieAsync()
               val work2 = repo.getPopularMovieAsync()
               val work3 = repo.getPopularMovieAsync()
               val work4 = repo.getPopularMovieAsync()
               retryIO {
                   _allHomeMovie.value = computeResults(work1.await().results, work2.await().results, work3.await().results, work4.await().results)
               }
           } catch (exception: Exception) {
               exception.printStackTrace()
           }
       }*/


/*
fun <T, U, S, K> computeResults(await1: T, await2: U, await3: S, await4: K): Pair<Pair<T, U>, Pair<S, K>> {
    return Pair(Pair(await1, await2), Pair(await3, await4))
}*/

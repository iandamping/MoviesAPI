package com.ian.app.muviepedia.util

import java.util.concurrent.TimeUnit

fun isExpireds(data: Long?): Boolean {
    val CACHE_EXPIRY = TimeUnit.HOURS.toMillis(1)
    return if (data != null) {
        (System.currentTimeMillis() - data) > CACHE_EXPIRY
    } else {
        false
    }
}

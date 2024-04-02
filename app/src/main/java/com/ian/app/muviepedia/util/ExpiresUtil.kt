package com.ian.app.muviepedia.util

import java.util.concurrent.TimeUnit

fun isExpireds(data: Long?): Boolean {
    val cacheExpiry = TimeUnit.HOURS.toMillis(1)
    return if (data != null) {
        (System.currentTimeMillis() - data) > cacheExpiry
    } else {
        false
    }
}

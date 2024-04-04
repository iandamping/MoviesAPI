package com.ian.app.muviepedia.util

fun reducingFraction(data: Double?): Double {
    return if (data != null) {
        val number2digits: Double = String.format("%.2f", data).toDouble()
        String.format("%.1f", number2digits).toDouble()
    } else {
        0.0
    }
}
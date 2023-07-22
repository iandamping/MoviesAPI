package com.ian.app.muviepedia.model

sealed class DataSource<out T> {
    data class Success<out T>(val data: T) : DataSource<T>()

    data class Error(val message: String) : DataSource<Nothing>()
}

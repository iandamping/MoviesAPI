package com.ian.app.muviepedia.core.presentation.model

sealed interface CommonResult<out T> {

    data class Success<T>(val data: T) : CommonResult<T>

    data class Failed(val errorMessage: String) : CommonResult<Nothing>
}
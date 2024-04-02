package com.ian.app.muviepedia.core.data.model

import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse

//todo : add new data class for receiving error from exception
sealed class RemoteBaseResult<out T> {

    data class Success<T>(val data: BaseResponse<T>?) : RemoteBaseResult<T>()

    data class Error(val message: String) : RemoteBaseResult<Nothing>()
}

sealed class RemoteResult<out T> {

    data class Success<T>(val data: T?) : RemoteResult<T>()

    data class Error(val message: String) : RemoteResult<Nothing>()
}

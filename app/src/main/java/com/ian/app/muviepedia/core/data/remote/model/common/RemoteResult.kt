package com.ian.app.muviepedia.core.data.remote.model.common

import com.ian.app.muviepedia.core.data.remote.model.BaseResponse
import retrofit2.Response

sealed class RemoteBaseResult<out T> {

    data class Success<T>(val data: Response<BaseResponse<T>>) : RemoteBaseResult<T>()

    data class Error(val exception: Exception) : RemoteBaseResult<Nothing>()

}

sealed class RemoteResult<out T> {

    data class Success<T>(val data: Response<T>) : RemoteResult<T>()

    data class Error(val exception: Exception) : RemoteResult<Nothing>()

}
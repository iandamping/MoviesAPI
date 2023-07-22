package com.ian.app.muviepedia.remote.helper

import com.ian.app.muviepedia.model.BaseResponse
import com.ian.app.muviepedia.model.RemoteBaseResult
import com.ian.app.muviepedia.model.RemoteResult
import retrofit2.Response

interface RemoteHelper {

    suspend fun  <T> remoteWithBaseCall(call: Response<BaseResponse<T>>): RemoteBaseResult<T>

    suspend fun  <T> remoteCall(call: Response<T>): RemoteResult<T>
}
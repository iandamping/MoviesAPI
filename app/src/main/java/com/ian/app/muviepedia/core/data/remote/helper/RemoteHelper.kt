package com.ian.app.muviepedia.core.data.remote.helper

import com.ian.app.muviepedia.core.data.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.remote.model.common.RemoteBaseResult
import com.ian.app.muviepedia.core.data.remote.model.common.RemoteResult
import retrofit2.Response

interface RemoteHelper {

    suspend fun  <T> remoteWithBaseCall(call: Response<BaseResponse<T>>): RemoteBaseResult<T>

    suspend fun  <T> remoteCall(call: Response<T>): RemoteResult<T>
}
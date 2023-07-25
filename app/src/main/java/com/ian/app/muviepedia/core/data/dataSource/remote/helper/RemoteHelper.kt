package com.ian.app.muviepedia.core.data.dataSource.remote.helper

import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.core.data.model.RemoteResult
import retrofit2.Response

interface RemoteHelper {

    suspend fun  <T> remoteWithBaseCall(call: Response<BaseResponse<T>>): RemoteBaseResult<T>

    suspend fun  <T> remoteCall(call: Response<T>): RemoteResult<T>
}
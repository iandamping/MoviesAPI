package com.ian.app.muviepedia.core.data.dataSource.remote.helper

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.core.data.model.RemoteResult
import com.ian.app.muviepedia.util.UtilityHelper
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteHelperImpl @Inject constructor(private val utilityHelper: UtilityHelper) :
    RemoteHelper {

    override suspend fun <T> remoteWithBaseCall(call: Response<BaseResponse<T>>): RemoteBaseResult<T> {
        return try {
            if (call.isSuccessful) {
                RemoteBaseResult.Success(call.body())
            } else {
                RemoteBaseResult.Error(
                    call.errorBody()?.string()
                            ?: utilityHelper.getString(R.string.default_error_message)

                )
            }
        } catch (e: SocketException) {
            RemoteBaseResult.Error(e.message.toString())
        } catch (e: UnknownHostException) {
            RemoteBaseResult.Error(e.message.toString())
        } catch (e: SocketTimeoutException) {
            RemoteBaseResult.Error(e.message.toString())
        } catch (e: IllegalArgumentException) {
            RemoteBaseResult.Error(e.message.toString())
        }
    }

    override suspend fun <T> remoteCall(call: Response<T>): RemoteResult<T> {
        return try {
            if (call.isSuccessful) {
                RemoteResult.Success(call.body())
            } else {
                RemoteResult.Error(
                        call.errorBody()?.string()
                            ?: utilityHelper.getString(R.string.default_error_message)
                )
            }
        } catch (e: SocketException) {
            RemoteResult.Error(e.message.toString())
        } catch (e: UnknownHostException) {
            RemoteResult.Error(e.message.toString())
        } catch (e: SocketTimeoutException) {
            RemoteResult.Error(e.message.toString())
        } catch (e: IllegalArgumentException) {
            RemoteResult.Error(e.message.toString())
        }
    }
}

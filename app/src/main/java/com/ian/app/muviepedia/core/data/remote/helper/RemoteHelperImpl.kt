package com.ian.app.muviepedia.core.data.remote.helper

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.core.data.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.remote.model.common.RemoteBaseResult
import com.ian.app.muviepedia.core.data.remote.model.common.RemoteResult
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
                return RemoteBaseResult.Success(call)
            } else return RemoteBaseResult.Error(
                Exception(
                    call.errorBody()?.string()
                        ?: utilityHelper.getString(R.string.default_error_message)
                )
            )
        } catch (e: Exception) {
            RemoteBaseResult.Error(Exception(utilityHelper.getString(R.string.default_error_message)))
        } catch (e: SocketException) {
            RemoteBaseResult.Error(Exception(utilityHelper.getString(R.string.check_internet_condition)))

        } catch (e: UnknownHostException) {
            RemoteBaseResult.Error(Exception(utilityHelper.getString(R.string.check_internet_condition)))

        } catch (e: SocketTimeoutException) {
            RemoteBaseResult.Error(Exception(utilityHelper.getString(R.string.check_internet_condition)))
        }
    }

    override suspend fun <T> remoteCall(call: Response<T>): RemoteResult<T> {
        return try {
            if (call.isSuccessful) {
                return RemoteResult.Success(call)
            } else return RemoteResult.Error(
                Exception(
                    call.errorBody()?.string()
                        ?: utilityHelper.getString(R.string.default_error_message)
                )
            )
        } catch (e: Exception) {
            RemoteResult.Error(Exception(utilityHelper.getString(R.string.default_error_message)))
        } catch (e: SocketException) {
            RemoteResult.Error(Exception(utilityHelper.getString(R.string.check_internet_condition)))

        } catch (e: UnknownHostException) {
            RemoteResult.Error(Exception(utilityHelper.getString(R.string.check_internet_condition)))

        } catch (e: SocketTimeoutException) {
            RemoteResult.Error(Exception(utilityHelper.getString(R.string.check_internet_condition)))
        }
    }
}
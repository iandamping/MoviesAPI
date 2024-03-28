package com.ian.app.muviepedia.util.parser

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.core.presentation.model.CommonResult
import com.ian.app.muviepedia.util.UtilityHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Inject

class MoshiParserImpl @Inject constructor(
    private val moshi: Moshi,
    private val utilityHelper: UtilityHelper
) : MoshiParser {

    override fun <T> fromJson(json: String, type: Type): CommonResult<T> {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter<T>(type).lenient()
        return try {
            val result = checkNotNull(jsonAdapter.fromJson(json))
            CommonResult.Success(result)
        } catch (e: IllegalStateException) {
            CommonResult.Failed(
                utilityHelper.getString(
                    resources = R.string.parsing_error_message,
                )
            )
        } catch (e: IOException) {
            CommonResult.Failed(
                utilityHelper.getString(
                    resources = R.string.parsing_error_message,
                )
            )
        } catch (e: JsonDataException) {
            CommonResult.Failed(
                utilityHelper.getString(
                    resources = R.string.parsing_error_message,
                )
            )
        }
    }

    override fun <T> toJson(obj: T, type: Type): CommonResult<String> {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter<T>(type).lenient()
        return try {
            val result = checkNotNull(jsonAdapter.toJson(obj))
            CommonResult.Success(result)
        } catch (e: IllegalStateException) {
            CommonResult.Failed(
                utilityHelper.getString(
                    resources = R.string.parsing_error_message,
                )
            )
        } catch (e: IOException) {
            CommonResult.Failed(
                utilityHelper.getString(
                    resources = R.string.parsing_error_message,
                )
            )
        }
    }


}

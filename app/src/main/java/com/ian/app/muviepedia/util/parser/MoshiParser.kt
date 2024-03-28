package com.ian.app.muviepedia.util.parser

import com.ian.app.muviepedia.core.presentation.model.CommonResult
import java.lang.reflect.Type

interface MoshiParser {

    fun <T> fromJson(json: String, type: Type): CommonResult<T>

    fun <T> toJson(obj: T, type: Type): CommonResult<String>
}

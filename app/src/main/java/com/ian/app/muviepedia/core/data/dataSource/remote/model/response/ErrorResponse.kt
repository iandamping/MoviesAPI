package com.ian.app.muviepedia.core.data.dataSource.remote.model.response

import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "success")
    val isSuccess:Boolean,
    @Json(name = "status_code")
    val statusCode:Int,
    @Json(name = "status_message")
    val message:String
)

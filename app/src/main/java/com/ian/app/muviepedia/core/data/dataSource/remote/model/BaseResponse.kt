package com.ian.app.muviepedia.core.data.dataSource.remote.model

import com.squareup.moshi.Json

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class BaseResponse<out T>(
    @Json(name = "page") val page: Int?,
    @Json(name = "total_results") val totalResults: Int?,
    @Json(name = "total_pages") var totalPages: Int?,
    @Json(name = "results") val results: List<T>
)

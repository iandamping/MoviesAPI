package com.ian.app.muviepedia.core.data.dataSource.remote.model.response

import com.squareup.moshi.Json

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class VideoDataResponse(
    @Json(name = "id") val id: String,
    @Json(name = "results") val results: List<ItemVideoDataResponse>,
) {
    data class ItemVideoDataResponse(
        @Json(name = "id") val id: String,
        @Json(name = "site") val site: String,
        @Json(name = "key") val key: String,
        @Json(name = "type") val type: String,
    )
}

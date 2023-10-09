package com.ian.app.muviepedia.core.data.dataSource.remote.model.response

import com.squareup.moshi.Json

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class MovieDataResponse(
    @Json(name = "vote_count") val vote_count: Int,
    @Json(name = "id") val id: Int?,
    @Json(name = "video") val video: Boolean?,
    @Json(name = "vote_average") val vote_average: Double?,
    @Json(name = "title") val title: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "poster_path") val poster_path: String?,
    @Json(name = "original_language") val original_language: String?,
    @Json(name = "original_title") val original_title: String?,
    @Json(name = "backdrop_path") val backdrop_path: String?,
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "release_date") val release_date: String?
)

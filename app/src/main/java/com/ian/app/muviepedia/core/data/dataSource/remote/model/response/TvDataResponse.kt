package com.ian.app.muviepedia.core.data.dataSource.remote.model.response

import com.squareup.moshi.Json


/**
 *
Created by Ian Damping on 18/06/2019.
Github = https://github.com/iandamping
 */
data class TvDataResponse(
    @Json(name = "original_name") val original_name: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "vote_count") val vote_count: Int?,
    @Json(name = "first_air_date") val first_air_date: String?,
    @Json(name = "backdrop_path") val backdrop_path: String?,
    @Json(name = "original_language") val original_language: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "vote_average") val vote_average: Double?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "poster_path") val poster_path: String?
) 

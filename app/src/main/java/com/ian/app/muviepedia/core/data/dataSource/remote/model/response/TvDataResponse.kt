package com.ian.app.muviepedia.core.data.dataSource.remote.model.response

import com.squareup.moshi.Json

/**
 *
Created by Ian Damping on 18/06/2019.
Github = https://github.com/iandamping
 */
data class TvDataResponse(
    @Json(name = "original_name") val originalName: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "vote_count") val voteCount: Int?,
    @Json(name = "first_air_date") val firstAirDate: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "vote_average") val voteAverage: Double?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "poster_path") val posterPath: String?
)

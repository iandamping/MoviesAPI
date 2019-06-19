package com.ian.app.muviepedia.data.model


/**
 *
Created by Ian Damping on 18/06/2019.
Github = https://github.com/iandamping
 */
data class TvData(var original_name: String?,
                  var genre_ids: List<Int>?,
                  var name: String?,
                  var popularity: Double?,
                  var origin_country: List<String>?,
                  var vote_count: Int?,
                  var first_air_date: String?,
                  var backdrop_path: String?,
                  var original_language: String?,
                  var id: Int?,
                  var vote_average: Double?,
                  var overview: String?,
                  var poster_path: String?) {
}

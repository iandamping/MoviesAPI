package com.ian.app.muviepedia.core.data.dataSource.remote.model.response

import com.squareup.moshi.Json

data class DetailTvResponse(
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "backdrop_path") val backdrop_path: String?,
    @Json(name = "belongs_to_collection") val belongs_to_collection: BelongsToCollectionResponse?,
    @Json(name = "budget") val budget: Int?,
    @Json(name = "genres") val genres: List<GenreResponse>,
    @Json(name = "id") val id: Int?,
    @Json(name = "imdb_id") val imdb_id: String?,
    @Json(name = "original_language") val original_language: String?,
    @Json(name = "original_title") val original_title: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "poster_path") val poster_path: String?,
    @Json(name = "production_companies") val production_companies: List<ProductionCompanyResponse>,
    @Json(name = "production_countries") val production_countries: List<ProductionCountryResponse>,
    @Json(name = "release_date") val release_date: String?,
    @Json(name = "revenue") val revenue: String?,
    @Json(name = "runtime") val runtime: Int?,
    @Json(name = "spoken_languages") val spoken_languages: List<SpokenLanguageResponse>,
    @Json(name = "status") val status: String?,
    @Json(name = "tagline") val tagline: String?,
    @Json(name = "name") val title: String?,
    @Json(name = "video") val video: Boolean?,
    @Json(name = "vote_average") val vote_average: Double?,
    @Json(name = "vote_count") val vote_count: Int?
) {
    data class BelongsToCollectionResponse(
        @Json(name = "id") val id: Int?,
        @Json(name = "name") val name: String?,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "backdrop_path") val backdropPath: String?
    )

    data class GenreResponse(@Json(name = "id") val id: Int?, @Json(name = "name") val name: String?)
    data class ProductionCompanyResponse(
        @Json(name = "id") val id: Int?,
        @Json(name = "logo_path") val logoPath: String?,
        @Json(name = "name") val name: String?,
        @Json(name = "origin_country") val originCountry: String?
    )

    data class ProductionCountryResponse(
        @Json(name = "iso31661") val iso31661: String?,
        @Json(name = "name") val name: String?
    )

    data class SpokenLanguageResponse(
        @Json(name = "iso31661") val iso31661: String?,
        val name: String?
    )
}

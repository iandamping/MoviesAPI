package com.ian.app.muviepedia.core.data.repository.model

import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse

data class TelevisionDetail(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: String,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    data class BelongsToCollection(
        val id: Int,
        val name: String,
        val posterPath: String,
        val backdropPath: String
    )

    data class Genre(val id: Int, val name: String)

    data class ProductionCompany(
        val id: Int,
        val logoPath: String,
        val name: String,
        val originCountry: String
    )

    data class ProductionCountry(
        val iso31661: String,
        val name: String
    )

    data class SpokenLanguage(
        val iso31661: String,
        val name: String
    )
}

fun DetailTvResponse.BelongsToCollectionResponse?.mapToDomain(): TelevisionDetail.BelongsToCollection {
    return TelevisionDetail.BelongsToCollection(
        this?.id ?: 0,
        this?.name ?: "",
        this?.posterPath ?: "",
        this?.backdropPath ?: ""
    )
}

fun DetailTvResponse.GenreResponse?.mapToDomain(): TelevisionDetail.Genre {
    return TelevisionDetail.Genre(this?.id ?: 0, this?.name ?: "")
}

fun DetailTvResponse.ProductionCompanyResponse?.mapToDomain(): TelevisionDetail.ProductionCompany {
    return TelevisionDetail.ProductionCompany(
        this?.id ?: 0,
        this?.logoPath ?: "",
        this?.name ?: "",
        this?.originCountry ?: ""
    )
}

fun DetailTvResponse.ProductionCountryResponse?.mapToDomain(): TelevisionDetail.ProductionCountry {
    return TelevisionDetail.ProductionCountry(
        this?.iso31661 ?: "",
        this?.name ?: ""
    )
}

fun DetailTvResponse.SpokenLanguageResponse?.mapToDomain(): TelevisionDetail.SpokenLanguage {
    return TelevisionDetail.SpokenLanguage(
        this?.iso31661 ?: "",
        this?.name ?: ""
    )
}

fun DetailTvResponse.mapToDomain(): TelevisionDetail {
    return TelevisionDetail(
        adult ?: false,
        backdrop_path ?: "",
        belongs_to_collection.mapToDomain(),
        budget ?: 0,
        genres.map { it.mapToDomain() },
        id ?: 0,
        imdb_id ?: "",
        original_language ?: "",
        original_title ?: "",
        overview ?: "",
        popularity ?: 0.0,
        poster_path ?: "",
        production_companies.map { it.mapToDomain() },
        production_countries.map { it.mapToDomain() },
        release_date ?: "",
        revenue ?: "",
        runtime ?: 0,
        spoken_languages.map { it.mapToDomain() },
        status ?: "",
        tagline ?: "",
        title ?: "",
        video ?: false,
        vote_average ?: 0.0,
        vote_count ?: 0
    )
}

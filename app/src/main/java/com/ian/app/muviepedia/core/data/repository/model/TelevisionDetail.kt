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


fun DetailTvResponse.BelongsToCollectionResponse.mapToDomain(): TelevisionDetail.BelongsToCollection {
    return TelevisionDetail.BelongsToCollection(
        id,
        name,
        posterPath,
        backdropPath
    )
}

fun DetailTvResponse.GenreResponse.mapToDomain(): TelevisionDetail.Genre {
    return TelevisionDetail.Genre(id, name)
}

fun DetailTvResponse.ProductionCompanyResponse.mapToDomain(): TelevisionDetail.ProductionCompany {
    return TelevisionDetail.ProductionCompany(
        id,
        logoPath,
        name,
        originCountry
    )
}

fun DetailTvResponse.ProductionCountryResponse.mapToDomain(): TelevisionDetail.ProductionCountry {
    return TelevisionDetail.ProductionCountry(
        iso31661,
        name
    )
}

fun DetailTvResponse.SpokenLanguageResponse.mapToDomain(): TelevisionDetail.SpokenLanguage {
    return TelevisionDetail.SpokenLanguage(
        iso31661,
        name
    )
}

fun DetailTvResponse.mapToDomain(): TelevisionDetail {
    return TelevisionDetail(
        adult,
        backdrop_path,
        belongs_to_collection.mapToDomain(),
        budget,
        genres.map { it.mapToDomain() },
        id,
        imdb_id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        production_companies.map { it.mapToDomain() },
        production_countries.map { it.mapToDomain() },
        release_date,
        revenue,
        runtime,
        spoken_languages.map { it.mapToDomain() },
        status,
        tagline,
        title,
        video,
        vote_average,
        vote_count
    )
}

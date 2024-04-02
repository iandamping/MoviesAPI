package com.ian.app.muviepedia.core.data.repository.model

import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse

data class TelevisionDetail(
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: String,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
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
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        belongsToCollection = belongsToCollection.mapToDomain(),
        budget = budget ?: 0,
        genres = genres.map { it.mapToDomain() },
        id = id ?: 0,
        imdbId = imdbId ?: "",
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        productionCompanies = productionCompanies.map { it.mapToDomain() },
        productionCountries = productionCountries.map { it.mapToDomain() },
        releaseDate = releaseDate ?: "",
        revenue = revenue ?: "",
        runtime = runtime ?: 0,
        spokenLanguages = spokenLanguages.map { it.mapToDomain() },
        status = status ?: "",
        tagline = tagline ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0
    )
}

package com.ian.app.muviepedia.core.data.repository.model

import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.util.reducingFraction

data class MovieDetail(
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

fun DetailMovieResponse.BelongsToCollectionResponse?.mapToDomain(): MovieDetail.BelongsToCollection {
    return MovieDetail.BelongsToCollection(
        this?.id ?: 0,
        this?.name ?: "",
        this?.posterPath ?: "",
        this?.backdropPath ?: ""
    )
}

fun DetailMovieResponse.GenreResponse?.mapToDomain(): MovieDetail.Genre {
    return MovieDetail.Genre(this?.id ?: 0, this?.name ?: "")
}

fun DetailMovieResponse.ProductionCompanyResponse?.mapToDomain(): MovieDetail.ProductionCompany {
    return MovieDetail.ProductionCompany(
        id = this?.id ?: 0,
        logoPath = "${NetworkConstant.smallImageFormatter}${this?.logoPath}",
        name = this?.name ?: "",
        originCountry = this?.originCountry ?: ""
    )
}

fun DetailMovieResponse.ProductionCountryResponse?.mapToDomain(): MovieDetail.ProductionCountry {
    return MovieDetail.ProductionCountry(
        this?.iso31661 ?: "",
        this?.name ?: ""
    )
}

fun DetailMovieResponse.SpokenLanguageResponse?.mapToDomain(): MovieDetail.SpokenLanguage {
    return MovieDetail.SpokenLanguage(
        this?.iso31661 ?: "",
        this?.name ?: ""
    )
}

fun DetailMovieResponse.mapToDomain(): MovieDetail {
    return MovieDetail(
        adult = adult ?: false,
        backdropPath = "${NetworkConstant.imageFormatter}$backdropPath",
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
        voteAverage = reducingFraction(voteAverage),
        voteCount = voteCount ?: 0
    )
}

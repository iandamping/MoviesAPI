package com.ian.app.muviepedia.feature.home.television

sealed class EpoxyPopularTelevisionData {

    data class Shimmer(val epoxyId: Int) : EpoxyPopularTelevisionData()

    data class TelevisionData(
        val epoxyId: Int,
        val originalName: String,
        val name: String,
        val popularity: Double,
        val voteCount: Int,
        val firstAirDate: String,
        val backdropPath: String,
        val originalLanguage: String,
        val id: Int,
        val voteAverage: Double,
        val overview: String,
        val posterPath: String
    ) : EpoxyPopularTelevisionData()

    object Error : EpoxyPopularTelevisionData()
}
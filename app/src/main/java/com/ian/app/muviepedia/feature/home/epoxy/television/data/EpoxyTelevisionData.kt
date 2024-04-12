package com.ian.app.muviepedia.feature.home.epoxy.television.data

sealed class EpoxyTelevisionData {

    data class Shimmer(val epoxyId: Int) : EpoxyTelevisionData()

    data class TelevisionData(
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
    ) : EpoxyTelevisionData()

    object Error : EpoxyTelevisionData()
}
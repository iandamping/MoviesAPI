package com.ian.app.muviepedia.feature.home.epoxy.television.topRated

sealed class EpoxyTopRatedTelevisionData {

    data class Shimmer(val epoxyId: Int) : EpoxyTopRatedTelevisionData()

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
    ) : EpoxyTopRatedTelevisionData()

    object Error : EpoxyTopRatedTelevisionData()
}

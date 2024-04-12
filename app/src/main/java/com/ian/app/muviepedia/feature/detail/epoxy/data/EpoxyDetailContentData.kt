package com.ian.app.muviepedia.feature.detail.epoxy.data


sealed class EpoxyDetailContentData {

    data class Shimmer(val epoxyId: Int) : EpoxyDetailContentData()

    data class MovieData(
        val epoxyContentId: Int,
        val title: String,
        val tagline: String,
        val overview: String,
        val voteAverage: Double,
        val releaseDate: String,
        val revenue: String,
    ) : EpoxyDetailContentData()

    data class TelevisionData(
        val epoxyContentId: Int,
        val title: String,
        val tagline: String,
        val overview: String,
        val voteAverage: Double,
        val firstAiringDate: String,
        val lastAiringDate: String,
        val numberOfEpisodes: String,
        val numberOfSeasons: String,
    ) : EpoxyDetailContentData()

    data class Error(val errorMessage: String) : EpoxyDetailContentData()
}

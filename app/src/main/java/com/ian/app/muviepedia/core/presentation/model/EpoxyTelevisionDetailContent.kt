package com.ian.app.muviepedia.core.presentation.model

data class EpoxyTelevisionDetailContent(
    val epoxyImageId: Int,
    val backdropPath: String,
    val epoxyContentId: Int,
    val title: String,
    val tagline: String,
    val overview: String,
    val voteAverage: Double,
    val firstAiringDate: String,
    val lastAiringDate: String,
    val numberOfEpisodes: String,
    val numberOfSeasons: String,
)
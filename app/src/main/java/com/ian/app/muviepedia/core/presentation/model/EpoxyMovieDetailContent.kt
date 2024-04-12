package com.ian.app.muviepedia.core.presentation.model

data class EpoxyMovieDetailContent(
    val epoxyImageId: Int,
    val epoxyContentId: Int,
    val backdropPath: String,
    val title: String,
    val tagline: String,
    val overview: String,
    val voteAverage: Double,
    val releaseDate: String,
    val revenue: String,
)

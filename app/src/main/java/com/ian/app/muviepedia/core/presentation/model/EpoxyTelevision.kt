package com.ian.app.muviepedia.core.presentation.model

data class EpoxyTelevision(
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
)

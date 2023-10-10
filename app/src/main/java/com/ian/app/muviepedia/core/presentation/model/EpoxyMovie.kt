package com.ian.app.muviepedia.core.presentation.model

data class EpoxyMovie(
    val epoxyId: Int,
    val voteCount: Int,
    val id: Int,
    val video: Boolean,
    val voteAverage: Double,
    val title: String,
    val popularity: Double,
    val posterPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String
)

package com.ian.app.muviepedia.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.model.TvData

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
object MovieConstant {
    const val delayMillis = 3000L
    const val imageFormatter = "https://image.tmdb.org/t/p/w500"
    /*Paging movie constant*/
    const val popularPagingState = "popular paging"
    const val topRatedPagingState = "top rated paging"
    const val upcomingPagingState = "upcoming paging"
    const val nowPlayingPagingState = "now playing paging"
    /*Paging tv constant*/
    const val popularTvPagingState = "popular tv paging"
    const val topRatedTvPagingState = "top rated tv paging"
    const val airingTodayPagingState = "airing today tv paging"
    const val onAirPagingState = "on air tv paging"

    const val intentToDiscoverActivity = "discover activity"
    const val intentToDiscoverTvActivity = "discover tv activity"
    const val intentToDetail = "intent detal"
    const val intentToTvDetail = "intent TV detal"
    const val saveUserProfile = " save user profile"
    const val prefHelperInit = " init preference helper"
    const val RequestSignIn = 2341
    const val switchBackToMain = " switching back"

    const val baseUrl = "https://api.themoviedb.org/3/"
    const val api_key = "9986464dc7ff8e83569e65a98dc7b3b6"
    const val popularMovie = "movie/popular"
    const val nowPlayingMovie = "movie/now_playing"
    const val topRatedMovie = "movie/top_rated"
    const val upComingMovie = "movie/upcoming"
    const val detailMovie = "movie/"
    const val similarMovie = "movie/"

    const val detailTv = "tv/"
    const val similarTv = "tv/"
    const val popularTv = "tv/popular"
    const val topRatedTv = "tv/top_rated"
    const val airingTodayTv = "tv/airing_today"
    const val onAirTv = "tv/on_the_air"

    val diffCallbacks = object : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
                oldItem.poster_path == newItem.poster_path

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
                oldItem.poster_path == newItem.poster_path
    }

    val movieDiffCallbacks = object : DiffUtil.ItemCallback<TvData>() {
        override fun areItemsTheSame(oldItem: TvData, newItem: TvData): Boolean =
                oldItem.poster_path == newItem.poster_path

        override fun areContentsTheSame(oldItem: TvData, newItem: TvData): Boolean =
                oldItem.poster_path == newItem.poster_path
    }
}
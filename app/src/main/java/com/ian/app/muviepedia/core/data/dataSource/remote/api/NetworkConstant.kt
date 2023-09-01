package com.ian.app.muviepedia.core.data.dataSource.remote.api

object NetworkConstant {
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
    const val searchMovie = "search/movie/"

    const val detailTv = "tv/"
    const val similarTv = "tv/"
    const val popularTv = "tv/popular"
    const val topRatedTv = "tv/top_rated"
    const val airingTodayTv = "tv/airing_today"
    const val onAirTv = "tv/on_the_air"
    const val searchTvShows = "search/tv/"
}
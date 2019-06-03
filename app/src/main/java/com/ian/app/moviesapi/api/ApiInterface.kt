package com.ian.app.moviesapi.api

import com.ian.app.moviesapi.data.model.GenericMovieModel
import com.ian.app.moviesapi.data.model.MovieData
import com.ian.app.moviesapi.di.NetworkModule
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
interface ApiInterface {
    @GET(NetworkModule.popularMovie)
    fun getPopularMovie(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.nowPlayingMovie)
    fun getNowPlayingMovie(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.topRatedMovie)
    fun getTopRatedMovie(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.upComingMovie)
    fun getUpComingMovie(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.latestMovie)
    fun getLatestMovie(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.popularMovie)
    fun pagingGetPopularMovie(@Query("api_key") apiKey: String, @Query("page") page: Int): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.topRatedMovie)
    fun pagingGetTopRatedMovie(@Query("api_key") apiKey: String, @Query("page") page: Int): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.upComingMovie)
    fun pagingGetUpComingMovie(@Query("api_key") apiKey: String, @Query("page") page: Int): Deferred<GenericMovieModel<MovieData>>

}
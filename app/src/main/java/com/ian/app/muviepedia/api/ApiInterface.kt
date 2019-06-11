package com.ian.app.muviepedia.api

import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.data.model.GenericMovieModel
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.di.NetworkModule
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
interface ApiInterface {
    @GET(NetworkModule.popularMovie)
    fun getPopularMovieAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.nowPlayingMovie)
    fun getNowPlayingMovieAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.topRatedMovie)
    fun getTopRatedMovieAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.upComingMovie)
    fun getUpComingMovieAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.detailMovie + "{movie}")
    fun getDetailMovieAsync(@Path("movie") movieId: Int, @Query("api_key") apiKey: String): Deferred<DetailMovieData>

    @GET(NetworkModule.similarMovie + "{movie_id}/similar")
    fun getSimilarMovieAsync(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.popularMovie)
    fun pagingGetPopularMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.topRatedMovie)
    fun pagingGetTopRatedMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): Deferred<GenericMovieModel<MovieData>>

    @GET(NetworkModule.upComingMovie)
    fun pagingGetUpComingMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): Deferred<GenericMovieModel<MovieData>>


}
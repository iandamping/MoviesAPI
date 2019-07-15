package com.ian.app.muviepedia.data.repo.movie

import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.data.model.GenericMovieModel
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.app.muviepedia.util.MovieConstant.api_key
import kotlinx.coroutines.Deferred

/**
 *
Created by Ian Damping on 09/07/2019.
Github = https://github.com/iandamping
 */
class MovieRepository(private val api: ApiInterface) {

    fun getDetailMovieAsync(movieId: Int): Deferred<DetailMovieData> {
        return api.getDetailMovieAsync(movieId, api_key)
    }

    fun getSimilarMovieAsync(movieId: Int):Deferred<GenericMovieModel<MovieData>>{
        return api.getSimilarMovieAsync(movieId, api_key)
    }


    fun getPopularMovieAsync():Deferred<GenericMovieModel<MovieData>>{
        return api.getPopularMovieAsync(api_key)
    }

    fun getNowPlayingAsync():Deferred<GenericMovieModel<MovieData>>{
        return api.getNowPlayingMovieAsync(api_key)
    }

    fun getTopRatedMovieAsync():Deferred<GenericMovieModel<MovieData>>{
        return api.getTopRatedMovieAsync(api_key)
    }

    fun getUpComingMovieAsync(): Deferred<GenericMovieModel<MovieData>> {
        return api.getUpComingMovieAsync(api_key)
    }

    fun getNowPlayingMoviePagingAsync(pageMovie:Int):Deferred<GenericMovieModel<MovieData>>{
        return api.pagingGetNowPlayingMovieMovieAsync(api_key, pageMovie)
    }

    fun getPopularMoviePagingAsync(pageMovie:Int):Deferred<GenericMovieModel<MovieData>>{
        return api.pagingGetPopularMovieAsync(api_key, pageMovie)
    }

    fun getTopRatedMoviePagingAsync(pageMovie:Int):Deferred<GenericMovieModel<MovieData>>{
        return api.pagingGetTopRatedMovieAsync(api_key, pageMovie)
    }

    fun getUpComingMoviePagingAsync(pageMovie:Int):Deferred<GenericMovieModel<MovieData>>{
        return api.pagingGetUpComingMovieAsync(api_key, pageMovie)
    }
}
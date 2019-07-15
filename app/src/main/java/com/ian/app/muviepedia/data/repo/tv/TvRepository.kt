package com.ian.app.muviepedia.data.repo.tv

import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.data.model.DetailTvData
import com.ian.app.muviepedia.data.model.GenericMovieModel
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.util.MovieConstant.api_key
import kotlinx.coroutines.Deferred

/**
 *
Created by Ian Damping on 09/07/2019.
Github = https://github.com/iandamping
 */
class TvRepository(private val api: ApiInterface) {

    fun getDetailTvAsync(tvID: Int): Deferred<DetailTvData> {
        return api.getDetailTvAsync(tvID, api_key)
    }

    fun getSimilarTvAsync(tvID: Int): Deferred<GenericMovieModel<TvData>> {
        return api.getSimilarTvAsync(tvID, api_key)
    }

    fun getPopularTvAsync(): Deferred<GenericMovieModel<TvData>> {
        return api.getPopularTvAsync(api_key)
    }

    fun getTopRatedTvAsync(): Deferred<GenericMovieModel<TvData>> {
        return api.getTopRatedTvAsync(api_key)
    }

    fun getAiringTodayTvAsync(): Deferred<GenericMovieModel<TvData>> {
        return api.getAiringTodayTvAsync(api_key)
    }

    fun getOnAirTvAsync(): Deferred<GenericMovieModel<TvData>> {
        return api.getOnAirTvAsync(api_key)
    }

    fun getAiringTodayPagingAsync(pageTv: Int): Deferred<GenericMovieModel<TvData>> {
        return api.pagingGetAiringTodayTvAsync(api_key, pageTv)
    }

    fun getOnAirPagingAsync(pageTv: Int): Deferred<GenericMovieModel<TvData>> {
        return api.pagingGetOnAirTvAsync(api_key, pageTv)
    }

    fun getPopularTvPagingAsync(pageTv: Int): Deferred<GenericMovieModel<TvData>> {
        return api.pagingGetPopularTvAsync(api_key, pageTv)
    }

    fun getTopRatedPagingAsync(pageTv: Int): Deferred<GenericMovieModel<TvData>> {
        return api.pagingGetTopRatedTvAsync(api_key, pageTv)
    }
}
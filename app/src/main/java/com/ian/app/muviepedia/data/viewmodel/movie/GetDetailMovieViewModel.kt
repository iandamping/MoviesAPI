package com.ian.app.muviepedia.data.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ian.app.helper.util.computeDoubleResult
import com.ian.app.helper.util.retryIO
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.repo.movie.MovieRepository
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 04/06/2019.
Github = https://github.com/iandamping
 */
class GetDetailMovieViewModel(private val repo: MovieRepository) : BaseViewModel() {
    private val _detailMovie: MutableLiveData<Pair<DetailMovieData, List<MovieData>>> = MutableLiveData()

    val detailMovie: LiveData<Pair<DetailMovieData, List<MovieData>>>
        get() = _detailMovie

    fun getData(movieId: Int) {
        viewModelScope.launch {
            retryIO { extractAllData(movieId) }

        }
    }

    private suspend fun extractAllData(movieId: Int) {
        val work1 = repo.getDetailMovieAsync(movieId)
        val work2 = repo.getSimilarMovieAsync(movieId)
        _detailMovie.value = computeDoubleResult(work1.await(), work2.await().results)
    }

}
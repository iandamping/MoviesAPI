package com.ian.app.muviepedia.data.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ian.app.helper.util.computeQuadResult
import com.ian.app.helper.util.retryIO
import com.ian.app.muviepedia.base.BaseViewModel
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.repo.movie.MovieRepository
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
class GetHomeMovieViewModel(private val repo: MovieRepository) : BaseViewModel() {
    private val ranges = 1..10
    private val _allHomeMovie: MutableLiveData<Pair<Pair<List<MovieData>, List<MovieData>>, Pair<List<MovieData>, List<MovieData>>>> = MutableLiveData()

    val allHomeMovie: LiveData<Pair<Pair<List<MovieData>, List<MovieData>>, Pair<List<MovieData>, List<MovieData>>>>
        get() = _allHomeMovie


    init {
        viewModelScope.launch {
            retryIO { getMovies() }
        }
    }

    private suspend fun getMovies() {
        val work1 = repo.getPopularMovieAsync()
        val work2 = repo.getNowPlayingAsync()
        val work3 = repo.getTopRatedMovieAsync()
        val work4 = repo.getUpComingMovieAsync()
        _allHomeMovie.value = computeQuadResult(popularMovieMapper(work1.await().results), nowPlayingMovieMapper(work2.await().results), topRatedMovieMapper(work3.await().results), upComingMovieMapper(work4.await().results))
    }

    private fun nowPlayingMovieMapper(data: List<MovieData>?): List<MovieData> {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun popularMovieMapper(data: List<MovieData>?): List<MovieData> {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun topRatedMovieMapper(data: List<MovieData>?): List<MovieData> {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }


    private fun upComingMovieMapper(data: List<MovieData>?): List<MovieData> {
        val newData: MutableList<MovieData> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        return newData
    }
}
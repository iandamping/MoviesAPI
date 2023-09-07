package com.ian.app.muviepedia.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.feature.search.controller.EpoxySearchData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private var job: Job? = null


    private val _epoxyMovieData: MutableStateFlow<EpoxySearchData> =
        MutableStateFlow(EpoxySearchData.init())
    val epoxyMovieData: StateFlow<EpoxySearchData>
        get() = _epoxyMovieData.asStateFlow()

    private fun setEpoxySearchMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                searchMovie = movieData,
                loading = emptyList(),
                error = emptyList()
            )
        }
    }


    private fun setEpoxySearchMovieLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                loading = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
                error = emptyList(),
                searchMovie = emptyList()
            )

        }
    }

    private fun setEpoxySearchMovieError(message: String) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                error = listOf(message),
                loading = emptyList(),
                searchMovie = emptyList()
            )

        }
    }

    fun searchMovie(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            repository.fetchSearchMovie(query).onStart { setEpoxySearchMovieLoading() }
                .onEach { result ->
                    when (result) {
                        is DomainSource.Error -> setEpoxySearchMovieError(result.message)
                        is DomainSource.Success -> setEpoxySearchMovieData(result.data)
                    }
                }.launchIn(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job = null
    }
}
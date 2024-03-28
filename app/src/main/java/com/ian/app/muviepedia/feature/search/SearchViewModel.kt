package com.ian.app.muviepedia.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.feature.search.controller.EpoxySearchData
import com.ian.app.muviepedia.feature.state.PresentationInputState
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

    private val _epoxyMovieData: MutableStateFlow<EpoxySearchData> =
        MutableStateFlow(EpoxySearchData.init())
    val epoxyMovieData: StateFlow<EpoxySearchData>
        get() = _epoxyMovieData.asStateFlow()

    private fun setEpoxySearchMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                uiState = PresentationInputState.Success,
                searchMovie = movieData,
            )
        }
    }

    private fun setEpoxySearchMovieLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                uiState = PresentationInputState.Loading
            )
        }
    }

    private fun setEpoxySearchMovieError(message: String) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                uiState = PresentationInputState.Failed,
                error = message,
            )
        }
    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            repository.fetchSearchMovie(query).onStart { setEpoxySearchMovieLoading() }
                .onEach { result ->
                    when (result) {
                        is DomainSource.Error -> setEpoxySearchMovieError(result.message)
                        is DomainSource.Success -> setEpoxySearchMovieData(result.data)
                    }
                }.launchIn(this)
        }
    }

}

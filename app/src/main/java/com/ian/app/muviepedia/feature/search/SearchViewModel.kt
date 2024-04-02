package com.ian.app.muviepedia.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
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
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _epoxySearchData: MutableStateFlow<EpoxySearchData> =
        MutableStateFlow(EpoxySearchData.init())
    val epoxySearchData: StateFlow<EpoxySearchData>
        get() = _epoxySearchData.asStateFlow()


    private fun setEpoxySearchMovieData(movieData: List<Movie>) {
        _epoxySearchData.update { uiState ->
            uiState.copy(
                flag = DetailFlag.MOVIE,
                uiState = PresentationInputState.Success,
                movieData = movieData,
            )
        }
    }

    private fun setEpoxySearchMovieLoading() {
        _epoxySearchData.update { uiState ->
            uiState.copy(
                flag = DetailFlag.MOVIE,
                uiState = PresentationInputState.Loading
            )
        }
    }

    private fun setEpoxySearchMovieError(message: String) {
        _epoxySearchData.update { uiState ->
            uiState.copy(
                flag = DetailFlag.MOVIE,
                uiState = PresentationInputState.Failed,
                error = message,
            )
        }
    }


    private fun setEpoxySearchTvData(tvData: List<Television>) {
        _epoxySearchData.update { uiState ->
            uiState.copy(
                flag = DetailFlag.TELEVISION,
                uiState = PresentationInputState.Success,
                tvData = tvData,
            )
        }
    }

    private fun setEpoxySearchTvLoading() {
        _epoxySearchData.update { uiState ->
            uiState.copy(
                flag = DetailFlag.TELEVISION,
                uiState = PresentationInputState.Loading
            )
        }
    }

    private fun setEpoxySearchTvError(message: String) {
        _epoxySearchData.update { uiState ->
            uiState.copy(
                flag = DetailFlag.TELEVISION,
                uiState = PresentationInputState.Failed,
                error = message,
            )
        }
    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            movieRepository.fetchSearchMovie(query).onStart { setEpoxySearchMovieLoading() }
                .onEach { result ->
                    when (result) {
                        is DomainSource.Error -> setEpoxySearchMovieError(result.message)
                        is DomainSource.Success -> setEpoxySearchMovieData(result.data)
                    }
                }.launchIn(this)
        }
    }

    fun searchTelevision(query: String) {
        viewModelScope.launch {
            tvRepository.fetchSearchTv(query).onStart { setEpoxySearchTvLoading() }
                .onEach { result ->
                    when (result) {
                        is DomainSource.Error -> setEpoxySearchTvError(result.message)
                        is DomainSource.Success -> setEpoxySearchTvData(result.data)
                    }
                }.launchIn(this)
        }
    }

}

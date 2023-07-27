package com.ian.app.muviepedia.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.feature.home.epoxy.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.state.PopularMovieUiState
import com.ian.app.muviepedia.feature.state.PresentationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val epoxyMapper: EpoxyMapper
) :
    ViewModel() {

    private val _popularMovieUiState: MutableStateFlow<PopularMovieUiState> = MutableStateFlow(
        PopularMovieUiState.initialize()
    )
    val popularMovieUiState: StateFlow<PopularMovieUiState> = _popularMovieUiState.asStateFlow()


    private val _epoxyPopularMovieData: MutableStateFlow<List<EpoxyPopularMovieData>> =
        MutableStateFlow(emptyList<EpoxyPopularMovieData>().toMutableList())
    val epoxyPopularMovieData: StateFlow<List<EpoxyPopularMovieData>> =
        _epoxyPopularMovieData.asStateFlow()


    suspend fun setEpoxyData(movieData: List<Movie>) {
        val listOfMovies = epoxyMapper.extractMovieToEpoxy(movieData)
        _epoxyPopularMovieData.update {
            epoxyMapper.epoxyPopularMovieListMapper(listOfMovies)
        }
    }


    fun setEpoxyLoading() {
        _epoxyPopularMovieData.update {
            mutableListOf(
                EpoxyPopularMovieData.Shimmer(0),
                EpoxyPopularMovieData.Shimmer(1),
                EpoxyPopularMovieData.Shimmer(2),
                EpoxyPopularMovieData.Shimmer(3),
                EpoxyPopularMovieData.Shimmer(4),
                EpoxyPopularMovieData.Shimmer(5),
                EpoxyPopularMovieData.Shimmer(6),
            )
        }
    }

    fun setEpoxyError() {
        _epoxyPopularMovieData.update {
            mutableListOf(EpoxyPopularMovieData.Error)
        }
    }

    init {
        viewModelScope.launch {
            movieRepository.fetchPopularMovie().collect {
                when (it) {
                    is DomainSource.Error -> _popularMovieUiState.update { uiState ->
                        uiState.copy(errorMessage = it.message, uiState = PresentationState.Failed)
                    }

                    is DomainSource.Success -> _popularMovieUiState.update { uiState ->
                        uiState.copy(data = it.data, uiState = PresentationState.Success)
                    }
                }
            }
        }
    }
}
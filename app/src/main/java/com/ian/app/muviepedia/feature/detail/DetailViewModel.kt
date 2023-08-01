package com.ian.app.muviepedia.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.core.presentation.model.GenericPairData
import com.ian.app.muviepedia.feature.state.DetailMovieUiState
import com.ian.app.muviepedia.feature.state.PresentationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val epoxyMapper: EpoxyMapper
) :
    ViewModel() {
    private val _detailMovieUiState: MutableStateFlow<DetailMovieUiState> = MutableStateFlow(
        DetailMovieUiState.initialize()
    )
    val detailMovieUiState: StateFlow<DetailMovieUiState> = _detailMovieUiState.asStateFlow()


    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            movieRepository.fetchDetailMovie(movieId = movieId)
                .combine(movieRepository.fetchSimilarMovie(movieId)) { a, b ->
                    GenericPairData(a, b)
                }.collect { data ->
                    when (data.data1) {
                        is DomainSource.Error -> _detailMovieUiState.update { uiState ->
                            uiState.copy(
                                uiState = PresentationState.Failed,
                                errorMessage = data.data1.message
                            )
                        }

                        is DomainSource.Success -> {
                            when (data.data2) {
                                is DomainSource.Error -> _detailMovieUiState.update { uiState ->
                                    uiState.copy(
                                        uiState = PresentationState.Failed,
                                        errorMessage = data.data2.message
                                    )
                                }

                                is DomainSource.Success -> {
                                    _detailMovieUiState.update { uiState ->
                                        uiState.copy(
                                            uiState = PresentationState.Success,
                                            data = data.data1.data,
                                            similarData = epoxyMapper.extractMovieToEpoxy(data.data2.data)
                                        )
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }

}
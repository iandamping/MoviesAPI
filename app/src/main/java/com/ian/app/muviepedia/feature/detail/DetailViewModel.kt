package com.ian.app.muviepedia.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.feature.state.DetailMovieUiState
import com.ian.app.muviepedia.feature.state.PresentationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _detailMovieUiState: MutableStateFlow<DetailMovieUiState> = MutableStateFlow(
        DetailMovieUiState.initialize()
    )
    val detailMovieUiState: StateFlow<DetailMovieUiState> = _detailMovieUiState.asStateFlow()


    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            movieRepository.fetchDetailMovie(movieId = movieId).collect { data ->
                when (data) {
                    is DomainSource.Error -> _detailMovieUiState.update { uiState ->
                        uiState.copy(
                            uiState = PresentationState.Failed,
                            errorMessage = data.message
                        )
                    }

                    is DomainSource.Success -> _detailMovieUiState.update { uiState ->
                        uiState.copy(uiState = PresentationState.Success, data = data.data)
                    }
                }
            }
        }
    }

}
package com.ian.app.muviepedia.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.core.presentation.model.GenericPairData
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.state.DetailMovieUiState
import com.ian.app.muviepedia.feature.state.PresentationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val televisionRepository: TvRepository,
    private val epoxyMapper: EpoxyMapper
) :
    ViewModel() {
    private val _detailMovieUiState: MutableStateFlow<DetailMovieUiState> = MutableStateFlow(
        DetailMovieUiState.initialize()
    )
    val detailMovieUiState: StateFlow<DetailMovieUiState> = _detailMovieUiState.asStateFlow()

    fun resetDetailMovieState(){
        _detailMovieUiState.update { DetailMovieUiState.initialize() }
    }


    fun getDetailMovie(id: Int, flag: DetailFlag) {
        viewModelScope.launch {
            if (flag == DetailFlag.MOVIE) {
                movieRepository.fetchDetailMovie(movieId = id)
                    .combine(movieRepository.fetchSimilarMovie(id)) { a, b ->
                        GenericPairData(a, b)
                    }.collectLatest { data ->
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
                                                flag = flag,
                                                uiState = PresentationState.Success,
                                                movieData = data.data1.data,
                                                similarMovieData = epoxyMapper.extractMovieToEpoxy(
                                                    data.data2.data
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

            } else {
                televisionRepository.fetchDetailTv(tvID = id)
                    .combine(televisionRepository.fetchSimilarTv(id)) { a, b ->
                        GenericPairData(a, b)
                    }.collectLatest { data ->
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
                                                flag = flag,
                                                uiState = PresentationState.Success,
                                                televisionData = data.data1.data,
                                                similarTelevisionData = epoxyMapper.extractTelevisionToEpoxy(
                                                    data.data2.data
                                                )
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

}
package com.ian.app.muviepedia.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.state.DetailCompanyDataUiState
import com.ian.app.muviepedia.feature.state.DetailDataUiState
import com.ian.app.muviepedia.feature.state.DetailSimilarDataUIState
import com.ian.app.muviepedia.feature.state.PresentationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val televisionRepository: TvRepository,
    private val epoxyMapper: EpoxyMapper
) :
    ViewModel() {
    private val _detailDataUiState: MutableStateFlow<DetailDataUiState> = MutableStateFlow(
        DetailDataUiState.initialize()
    )
    val detailDataUiState: StateFlow<DetailDataUiState> get() = _detailDataUiState.asStateFlow()

    private val _detailSimilarDataUiState: MutableStateFlow<DetailSimilarDataUIState> =
        MutableStateFlow(
            DetailSimilarDataUIState.initialize()
        )
    val detailSimilarDataUiState: StateFlow<DetailSimilarDataUIState> get() = _detailSimilarDataUiState.asStateFlow()

    private val _detailCompanyDataUiState: MutableStateFlow<DetailCompanyDataUiState> =
        MutableStateFlow(
            DetailCompanyDataUiState.initialize()
        )
    val detailCompanyDataUiState: StateFlow<DetailCompanyDataUiState> get() = _detailCompanyDataUiState.asStateFlow()

    private fun CoroutineScope.fetchMovie(id: Int, flag: DetailFlag) {
        launch {
            movieRepository.fetchDetailMovie(movieId = id).onStart {
                _detailDataUiState.update { detailDataUiState ->
                    detailDataUiState.copy(uiState = PresentationState.Loading)
                }
                _detailCompanyDataUiState.update { uiState ->
                    uiState.copy(uiState = PresentationState.Loading)
                }
            }
                .onEach { data ->
                    when (data) {
                        is DomainSource.Error -> {
                            _detailDataUiState.update { uiState ->
                                uiState.copy(
                                    uiState = PresentationState.Failed,
                                    errorMessage = data.message
                                )
                            }

                            _detailCompanyDataUiState.update { uiState ->
                                uiState.copy(
                                    uiState = PresentationState.Failed,
                                    errorMessage = data.message
                                )
                            }
                        }

                        is DomainSource.Success -> {
                            _detailDataUiState.update { uiState ->
                                uiState.copy(
                                    flag = flag,
                                    uiState = PresentationState.Success,
                                    movieData = data.data,
                                )
                            }

                            _detailCompanyDataUiState.update { uiState ->
                                uiState.copy(
                                    flag = flag,
                                    uiState = PresentationState.Success,
                                    movieCompany = data.data.productionCompanies
                                )
                            }
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun CoroutineScope.fetchSimilarMovie(id: Int, flag: DetailFlag) {
        launch {
            movieRepository.fetchSimilarMovie(movieId = id).onStart {
                _detailSimilarDataUiState.update { detailSimilarDataUiState ->
                    detailSimilarDataUiState.copy(uiState = PresentationState.Loading)
                }
            }
                .onEach { data ->
                    when (data) {
                        is DomainSource.Error -> _detailSimilarDataUiState.update { uiState ->
                            uiState.copy(
                                uiState = PresentationState.Failed,
                                errorMessage = data.message
                            )
                        }

                        is DomainSource.Success -> _detailSimilarDataUiState.update { uiState ->
                            uiState.copy(
                                flag = flag,
                                uiState = PresentationState.Success,
                                similarMovieData = epoxyMapper.extractMovieToEpoxy(
                                    data.data
                                ),
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun CoroutineScope.fetchTelevision(id: Int, flag: DetailFlag) {
        launch {
            televisionRepository.fetchDetailTv(tvID = id).onStart {
                _detailDataUiState.update { uiState ->
                    uiState.copy(
                        uiState = PresentationState.Loading
                    )
                }
                _detailCompanyDataUiState.update { uiState ->
                    uiState.copy(
                        uiState = PresentationState.Loading
                    )
                }
            }
                .onEach { data ->
                    when (data) {
                        is DomainSource.Error -> {
                            _detailDataUiState.update { uiState ->
                                uiState.copy(
                                    uiState = PresentationState.Failed,
                                    errorMessage = data.message
                                )
                            }

                            _detailCompanyDataUiState.update { uiState ->
                                uiState.copy(
                                    uiState = PresentationState.Failed,
                                    errorMessage = data.message
                                )
                            }
                        }

                        is DomainSource.Success -> {
                            _detailDataUiState.update { uiState ->
                                uiState.copy(
                                    flag = flag,
                                    uiState = PresentationState.Success,
                                    televisionData = data.data,
                                )
                            }

                            _detailCompanyDataUiState.update { uiState ->
                                uiState.copy(
                                    flag = flag,
                                    uiState = PresentationState.Success,
                                    televisionCompany = data.data.productionCompanies,
                                )
                            }
                        }
                    }
                }
                .launchIn(this)
        }
    }

    private fun CoroutineScope.fetchSimilarTelevision(id: Int, flag: DetailFlag) {
        launch {
            televisionRepository.fetchSimilarTv(tvID = id).onStart {
                _detailSimilarDataUiState.update { uiState ->
                    uiState.copy(
                        uiState = PresentationState.Loading
                    )
                }
            }
                .onEach { data ->
                    when (data) {
                        is DomainSource.Error -> _detailSimilarDataUiState.update { uiState ->
                            uiState.copy(
                                uiState = PresentationState.Failed,
                                errorMessage = data.message
                            )
                        }

                        is DomainSource.Success -> _detailSimilarDataUiState.update { uiState ->
                            uiState.copy(
                                flag = flag,
                                uiState = PresentationState.Success,
                                similarTelevisionData = epoxyMapper.extractTelevisionToEpoxy(
                                    data.data
                                ),
                            )
                        }
                    }
                }
                .launchIn(this)
        }
    }


    fun getDetailMovie(id: Int, flag: DetailFlag) {
        viewModelScope.launch {
            if (flag == DetailFlag.MOVIE) {
                fetchMovie(id = id, flag = flag)
                fetchSimilarMovie(id = id, flag = flag)
            } else {
                fetchTelevision(id = id, flag = flag)
                fetchSimilarTelevision(id = id, flag = flag)
            }
        }
    }
}

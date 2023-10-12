package com.ian.app.muviepedia.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyHomeMovieSetter
import com.ian.app.muviepedia.core.presentation.EpoxyHomeTelevisionSetter
import com.ian.app.muviepedia.feature.home.epoxy.controller.EpoxyHomeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository,
    private val epoxyMovieSetter: EpoxyHomeMovieSetter,
    private val epoxyTelevisionSetter: EpoxyHomeTelevisionSetter
) :
    ViewModel() {

    private val _epoxyMovieData: MutableStateFlow<EpoxyHomeData> =
        MutableStateFlow(EpoxyHomeData.init())
    val epoxyMovieData: StateFlow<EpoxyHomeData>
        get() = _epoxyMovieData.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                movieRepository.fetchPopularMovie().onStart {
                    _epoxyMovieData.update { uiState ->
                        uiState.copy(
                            popularMovie = epoxyMovieSetter.setEpoxyPopularMovieLoading()
                        )
                    }
                }.onEach {
                    when (it) {
                        is DomainSource.Error -> _epoxyMovieData.update { uiState ->
                            uiState.copy(
                                popularMovie = epoxyMovieSetter.setEpoxyPopularMovieError(),
                            )
                        }

                        is DomainSource.Success -> _epoxyMovieData.update { uiState ->
                            uiState.copy(
                                popularMovie = epoxyMovieSetter.setEpoxyPopularMovieData(it.data)
                            )
                        }
                    }
                }.launchIn(this)
            }
            launch {
                movieRepository.fetchNowPlayingMovie().onStart {
                    _epoxyMovieData.update { uiState ->
                        uiState.copy(
                            nowPlayingMovie = epoxyMovieSetter.setEpoxyNowPlayingMovieLoading()
                        )
                    }
                }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    nowPlayingMovie = epoxyMovieSetter.setEpoxyNowPlayingMovieError()
                                )
                            }

                            is DomainSource.Success -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    nowPlayingMovie = epoxyMovieSetter.setEpoxyNowPlayingMovieData(
                                        it.data
                                    )
                                )
                            }
                        }
                    }.launchIn(this)
            }
            launch {
                movieRepository.fetchTopRatedMovie().onStart {
                    _epoxyMovieData.update { uiState ->
                        uiState.copy(
                            topRatedMovie = epoxyMovieSetter.setEpoxyTopRatedMovieLoading()
                        )
                    }
                }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    topRatedMovie = epoxyMovieSetter.setEpoxyTopRatedMovieError()
                                )
                            }

                            is DomainSource.Success -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    topRatedMovie = epoxyMovieSetter.setEpoxyTopRatedMovieData(it.data)
                                )
                            }
                        }
                    }.launchIn(this)
            }
            launch {
                movieRepository.fetchUpComingMovie().onStart {
                    _epoxyMovieData.update { uiState ->
                        uiState.copy(
                            upComingMovie = epoxyMovieSetter.setEpoxyUpComingMovieLoading()
                        )
                    }
                }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    upComingMovie = epoxyMovieSetter.setEpoxyUpComingMovieError()
                                )
                            }

                            is DomainSource.Success -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    upComingMovie = epoxyMovieSetter.setEpoxyUpComingMovieData(it.data)
                                )
                            }
                        }
                    }.launchIn(this)
            }
            launch {
                tvRepository.prefetchPopularTv().onStart {
                    _epoxyMovieData.update { uiState ->
                        uiState.copy(
                            popularTelevision = epoxyTelevisionSetter.setEpoxyPopularTelevisionLoading()
                        )
                    }
                }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    popularTelevision = epoxyTelevisionSetter.setEpoxyPopularTelevisionError()
                                )
                            }

                            is DomainSource.Success -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    popularTelevision = epoxyTelevisionSetter.setEpoxyPopularTelevisionData(
                                        it.data
                                    )
                                )
                            }
                        }
                    }.launchIn(this)
            }
            launch {
                tvRepository.prefetchTopRatedTv().onStart {
                    _epoxyMovieData.update { uiState ->
                        uiState.copy(
                            topRatedTelevision = epoxyTelevisionSetter.setEpoxyTopRatedTelevisionLoading()
                        )
                    }
                }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    topRatedTelevision = epoxyTelevisionSetter.setEpoxyTopRatedTelevisionError()
                                )
                            }

                            is DomainSource.Success -> _epoxyMovieData.update { uiState ->
                                uiState.copy(
                                    topRatedTelevision = epoxyTelevisionSetter.setEpoxyTopRatedTelevisionData(
                                        it.data
                                    )
                                )
                            }
                        }
                    }.launchIn(this)
            }
        }
    }
}

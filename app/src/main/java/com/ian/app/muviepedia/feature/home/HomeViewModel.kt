package com.ian.app.muviepedia.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.feature.home.epoxy.controller.EpoxyHomeData
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData
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
    private val epoxyMapper: EpoxyMapper
) :
    ViewModel() {

    private val _epoxyMovieData: MutableStateFlow<EpoxyHomeData> =
        MutableStateFlow(EpoxyHomeData.init())
    val epoxyMovieData: StateFlow<EpoxyHomeData>
        get() = _epoxyMovieData.asStateFlow()

    private fun setEpoxyPopularMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                popularMovie = epoxyMapper.epoxyPopularMovieListMapper(
                    epoxyMapper.extractMovieToEpoxy(
                        movieData
                    )
                )
            )
        }
    }

    private fun setEpoxyPopularMovieLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                popularMovie = mutableListOf(
                    EpoxyPopularMovieData.Shimmer(epoxyId = 0),
                    EpoxyPopularMovieData.Shimmer(epoxyId = 1),
                    EpoxyPopularMovieData.Shimmer(epoxyId = 2),
                    EpoxyPopularMovieData.Shimmer(epoxyId = 3),
                    EpoxyPopularMovieData.Shimmer(epoxyId = 4),
                    EpoxyPopularMovieData.Shimmer(epoxyId = 5),
                    EpoxyPopularMovieData.Shimmer(epoxyId = 6),
                )
            )
        }
    }

    private fun setEpoxyPopularMovieError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                popularMovie = mutableListOf(EpoxyPopularMovieData.Error),
            )
        }
    }

    private fun setEpoxyNowPlayingMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                nowPlayingMovie = epoxyMapper.epoxyNowPlayingMovieListMapper(
                    epoxyMapper.extractMovieToEpoxy(
                        movieData
                    )
                )
            )
        }
    }

    private fun setEpoxyNowPlayingMovieLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                nowPlayingMovie = mutableListOf(
                    EpoxyNowPlayingMovieData.Shimmer(epoxyId = 0),
                    EpoxyNowPlayingMovieData.Shimmer(epoxyId = 1),
                    EpoxyNowPlayingMovieData.Shimmer(epoxyId = 2),
                    EpoxyNowPlayingMovieData.Shimmer(epoxyId = 3),
                    EpoxyNowPlayingMovieData.Shimmer(epoxyId = 4),
                    EpoxyNowPlayingMovieData.Shimmer(epoxyId = 5),
                    EpoxyNowPlayingMovieData.Shimmer(epoxyId = 6),
                )
            )
        }
    }

    private fun setEpoxyNowPlayingMovieError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                nowPlayingMovie = mutableListOf(EpoxyNowPlayingMovieData.Error)
            )
        }
    }

    private fun setEpoxyTopRatedMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                topRatedMovie = epoxyMapper.epoxyTopRatedMovieListMapper(
                    epoxyMapper.extractMovieToEpoxy(
                        movieData
                    )
                )
            )
        }
    }

    private fun setEpoxyTopRatedMovieLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                topRatedMovie = mutableListOf(
                    EpoxyTopRatedMovieData.Shimmer(epoxyId = 0),
                    EpoxyTopRatedMovieData.Shimmer(epoxyId = 1),
                    EpoxyTopRatedMovieData.Shimmer(epoxyId = 2),
                    EpoxyTopRatedMovieData.Shimmer(epoxyId = 3),
                    EpoxyTopRatedMovieData.Shimmer(epoxyId = 4),
                    EpoxyTopRatedMovieData.Shimmer(epoxyId = 5),
                    EpoxyTopRatedMovieData.Shimmer(epoxyId = 6),
                )
            )
        }
    }

    private fun setEpoxyTopRatedMovieError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                topRatedMovie = mutableListOf(EpoxyTopRatedMovieData.Error)
            )
        }
    }

    private fun setEpoxyUpComingMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                upComingMovie = epoxyMapper.epoxyUpComingMovieListMapper(
                    epoxyMapper.extractMovieToEpoxy(
                        movieData
                    )
                )
            )
        }
    }

    private fun setEpoxyUpComingMovieLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                upComingMovie = mutableListOf(
                    EpoxyUpComingMovieData.Shimmer(epoxyId = 0),
                    EpoxyUpComingMovieData.Shimmer(epoxyId = 1),
                    EpoxyUpComingMovieData.Shimmer(epoxyId = 2),
                    EpoxyUpComingMovieData.Shimmer(epoxyId = 3),
                    EpoxyUpComingMovieData.Shimmer(epoxyId = 4),
                    EpoxyUpComingMovieData.Shimmer(epoxyId = 5),
                    EpoxyUpComingMovieData.Shimmer(epoxyId = 6),
                )
            )
        }
    }

    private fun setEpoxyUpComingMovieError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                upComingMovie = mutableListOf(EpoxyUpComingMovieData.Error)
            )
        }
    }

    private fun setEpoxyPopularTelevisionData(televisionData: List<Television>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                popularTelevision = epoxyMapper.epoxyPopularTVListMapper(
                    epoxyMapper.extractTelevisionToEpoxy(
                        televisionData
                    )
                )
            )
        }
    }

    private fun setEpoxyPopularTelevisionLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                popularTelevision = mutableListOf(
                    EpoxyPopularTelevisionData.Shimmer(epoxyId = 0),
                    EpoxyPopularTelevisionData.Shimmer(epoxyId = 1),
                    EpoxyPopularTelevisionData.Shimmer(epoxyId = 2),
                    EpoxyPopularTelevisionData.Shimmer(epoxyId = 3),
                    EpoxyPopularTelevisionData.Shimmer(epoxyId = 4),
                    EpoxyPopularTelevisionData.Shimmer(epoxyId = 5),
                    EpoxyPopularTelevisionData.Shimmer(epoxyId = 6),
                )
            )
        }
    }

    private fun setEpoxyPopularTelevisionError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                popularTelevision = mutableListOf(EpoxyPopularTelevisionData.Error)
            )
        }
    }

    private fun setEpoxyTopRatedTelevisionData(televisionData: List<Television>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                topRatedTelevision = epoxyMapper.epoxyTopRatedTvListMapper(
                    epoxyMapper.extractTelevisionToEpoxy(
                        televisionData
                    )
                )
            )
        }
    }

    private fun setEpoxyTopRatedTelevisionLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                topRatedTelevision = mutableListOf(
                    EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 0),
                    EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 1),
                    EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 2),
                    EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 3),
                    EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 4),
                    EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 5),
                    EpoxyTopRatedTelevisionData.Shimmer(epoxyId = 6),
                )
            )
        }
    }

    private fun setEpoxyTopRatedTelevisionError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                topRatedTelevision = mutableListOf(EpoxyTopRatedTelevisionData.Error)
            )
        }
    }
    init {
        viewModelScope.launch {
            launch {
                movieRepository.fetchPopularMovie().onStart { setEpoxyPopularMovieLoading() }.onEach {
                    when (it) {
                        is DomainSource.Error -> setEpoxyPopularMovieError()
                        is DomainSource.Success -> setEpoxyPopularMovieData(it.data)
                    }
                }.launchIn(this)
            }
            launch {
                movieRepository.fetchNowPlayingMovie().onStart { setEpoxyNowPlayingMovieLoading() }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> setEpoxyNowPlayingMovieError()
                            is DomainSource.Success -> setEpoxyNowPlayingMovieData(it.data)
                        }
                    }.launchIn(this)
            }
            launch {
                movieRepository.fetchTopRatedMovie().onStart { setEpoxyTopRatedMovieLoading() }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> setEpoxyTopRatedMovieError()
                            is DomainSource.Success -> setEpoxyTopRatedMovieData(it.data)
                        }
                    }.launchIn(this)
            }
            launch {
                movieRepository.fetchUpComingMovie().onStart { setEpoxyUpComingMovieLoading() }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> setEpoxyUpComingMovieError()
                            is DomainSource.Success -> setEpoxyUpComingMovieData(it.data)
                        }
                    }.launchIn(this)
            }
            launch {
                tvRepository.prefetchPopularTv().onStart { setEpoxyPopularTelevisionLoading() }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> setEpoxyPopularTelevisionError()
                            is DomainSource.Success -> setEpoxyPopularTelevisionData(it.data)
                        }
                    }.launchIn(this)
            }
            launch {
                tvRepository.prefetchTopRatedTv().onStart { setEpoxyTopRatedTelevisionLoading() }
                    .onEach {
                        when (it) {
                            is DomainSource.Error -> setEpoxyTopRatedTelevisionError()
                            is DomainSource.Success -> setEpoxyTopRatedTelevisionData(it.data)
                        }
                    }.launchIn(this)
            }
        }
    }
}

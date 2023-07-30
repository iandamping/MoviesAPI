package com.ian.app.muviepedia.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.feature.home.epoxy.carousel.EpoxyHomeCarouselData
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxyUpComingMovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val epoxyMapper: EpoxyMapper
) :
    ViewModel() {

    private val _epoxyMovieData: MutableStateFlow<EpoxyHomeCarouselData> =
        MutableStateFlow(EpoxyHomeCarouselData.init())
    val epoxyMovieData: StateFlow<EpoxyHomeCarouselData> =
        _epoxyMovieData.asStateFlow()


    private fun setEpoxyPopularMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                popular = epoxyMapper.epoxyPopularMovieListMapper(
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
                popular = mutableListOf(
                    EpoxyPopularMovieData.Shimmer(0),
                    EpoxyPopularMovieData.Shimmer(1),
                    EpoxyPopularMovieData.Shimmer(2),
                    EpoxyPopularMovieData.Shimmer(3),
                    EpoxyPopularMovieData.Shimmer(4),
                    EpoxyPopularMovieData.Shimmer(5),
                    EpoxyPopularMovieData.Shimmer(6),
                )
            )

        }
    }

    private fun setEpoxyPopularMovieError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(popular = mutableListOf(EpoxyPopularMovieData.Error))

        }
    }


    private fun setEpoxyNowPlayingMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                nowPlaying = epoxyMapper.epoxyNowPlayingMovieListMapper(
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
                nowPlaying = mutableListOf(
                    EpoxyNowPlayingMovieData.Shimmer(0),
                    EpoxyNowPlayingMovieData.Shimmer(1),
                    EpoxyNowPlayingMovieData.Shimmer(2),
                    EpoxyNowPlayingMovieData.Shimmer(3),
                    EpoxyNowPlayingMovieData.Shimmer(4),
                    EpoxyNowPlayingMovieData.Shimmer(5),
                    EpoxyNowPlayingMovieData.Shimmer(6),
                )
            )

        }
    }

    private fun setEpoxyNowPlayingMovieError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(nowPlaying = mutableListOf(EpoxyNowPlayingMovieData.Error))

        }
    }

    private fun setEpoxyTopRatedMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                topRated = epoxyMapper.epoxyTopRatedMovieListMapper(
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
                topRated = mutableListOf(
                    EpoxyTopRatedMovieData.Shimmer(0),
                    EpoxyTopRatedMovieData.Shimmer(1),
                    EpoxyTopRatedMovieData.Shimmer(2),
                    EpoxyTopRatedMovieData.Shimmer(3),
                    EpoxyTopRatedMovieData.Shimmer(4),
                    EpoxyTopRatedMovieData.Shimmer(5),
                    EpoxyTopRatedMovieData.Shimmer(6),
                )
            )

        }
    }

    private fun setEpoxyTopRatedMovieError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(topRated = mutableListOf(EpoxyTopRatedMovieData.Error))
        }
    }

    private fun setEpoxyUpComingMovieData(movieData: List<Movie>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                upComing = epoxyMapper.epoxyUpComingMovieListMapper(
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
                upComing = mutableListOf(
                    EpoxyUpComingMovieData.Shimmer(0),
                    EpoxyUpComingMovieData.Shimmer(1),
                    EpoxyUpComingMovieData.Shimmer(2),
                    EpoxyUpComingMovieData.Shimmer(3),
                    EpoxyUpComingMovieData.Shimmer(4),
                    EpoxyUpComingMovieData.Shimmer(5),
                    EpoxyUpComingMovieData.Shimmer(6),
                )
            )

        }
    }

    private fun setEpoxyUpComingMovieError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(upComing = mutableListOf(EpoxyUpComingMovieData.Error))
        }
    }

    init {
        viewModelScope.launch {
            movieRepository.fetchPopularMovie().onStart { setEpoxyPopularMovieLoading() }.collect {
                when (it) {
                    is DomainSource.Error -> setEpoxyPopularMovieError()
                    is DomainSource.Success -> setEpoxyPopularMovieData(it.data)

                }
            }
        }

        viewModelScope.launch {
            movieRepository.fetchNowPlayingMovie().onStart { setEpoxyNowPlayingMovieLoading() }
                .collect {
                    when (it) {
                        is DomainSource.Error -> setEpoxyNowPlayingMovieError()
                        is DomainSource.Success -> setEpoxyNowPlayingMovieData(it.data)
                    }
                }
        }


        viewModelScope.launch {
            movieRepository.fetchTopRatedMovie().onStart { setEpoxyTopRatedMovieLoading() }
                .collect {
                    when (it) {
                        is DomainSource.Error -> setEpoxyTopRatedMovieError()
                        is DomainSource.Success -> setEpoxyTopRatedMovieData(it.data)

                    }
                }
        }


        viewModelScope.launch {
            movieRepository.fetchUpComingMovie().onStart { setEpoxyUpComingMovieLoading() }
                .collect {
                    when (it) {
                        is DomainSource.Error -> setEpoxyUpComingMovieError()
                        is DomainSource.Success -> setEpoxyUpComingMovieData(it.data)

                    }
                }
        }
    }
}
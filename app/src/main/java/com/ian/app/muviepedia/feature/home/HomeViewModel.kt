package com.ian.app.muviepedia.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.feature.home.epoxy.carousel.EpoxyHomeCarouselData
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.television.EpoxyPopularTelevisionData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _epoxyMovieData: MutableStateFlow<EpoxyHomeCarouselData> =
        MutableStateFlow(EpoxyHomeCarouselData.init())
    val epoxyMovieData: StateFlow<EpoxyHomeCarouselData> =
        _epoxyMovieData.asStateFlow()


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
            uiState.copy(popularMovie = mutableListOf(EpoxyPopularMovieData.Error))

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
            uiState.copy(nowPlayingMovie = mutableListOf(EpoxyNowPlayingMovieData.Error))

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
            uiState.copy(topRatedMovie = mutableListOf(EpoxyTopRatedMovieData.Error))
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


    private fun setEpoxyPopularTelevisionData(televisionData: List<Television>) {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                popularTelevision = epoxyMapper.epoxyPopularTelevisionListMapper(
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
                    EpoxyPopularTelevisionData.Shimmer(0),
                    EpoxyPopularTelevisionData.Shimmer(1),
                    EpoxyPopularTelevisionData.Shimmer(2),
                    EpoxyPopularTelevisionData.Shimmer(3),
                    EpoxyPopularTelevisionData.Shimmer(4),
                    EpoxyPopularTelevisionData.Shimmer(5),
                    EpoxyPopularTelevisionData.Shimmer(6),
                )
            )

        }
    }

    private fun setEpoxyPopularTelevisionError() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(popularTelevision = mutableListOf(EpoxyPopularTelevisionData.Error))

        }
    }

    private fun setEpoxyUpComingMovieLoading() {
        _epoxyMovieData.update { uiState ->
            uiState.copy(
                upComingMovie = mutableListOf(
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
            uiState.copy(upComingMovie = mutableListOf(EpoxyUpComingMovieData.Error))
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

        viewModelScope.launch {
            tvRepository.prefetchPopularTv().onStart { setEpoxyPopularTelevisionLoading() }
                .collect {
                    when (it) {
                        is DomainSource.Error -> setEpoxyPopularTelevisionError()
                        is DomainSource.Success -> setEpoxyPopularTelevisionData(it.data)
                    }
                }
        }

    }
}
package com.ian.app.muviepedia.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.EpoxyMapper
import com.ian.app.muviepedia.feature.home.epoxy.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.popular.EpoxyPopularMovieData
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

    private val _epoxyPopularMovieData: MutableStateFlow<List<EpoxyPopularMovieData>> =
        MutableStateFlow(emptyList<EpoxyPopularMovieData>().toMutableList())
    val epoxyPopularMovieData: StateFlow<List<EpoxyPopularMovieData>> =
        _epoxyPopularMovieData.asStateFlow()

    private val _epoxyNowPlayingMovieData: MutableStateFlow<List<EpoxyNowPlayingMovieData>> =
        MutableStateFlow(emptyList<EpoxyNowPlayingMovieData>().toMutableList())
    val epoxyNowPlayingMovieData: StateFlow<List<EpoxyNowPlayingMovieData>> =
        _epoxyNowPlayingMovieData.asStateFlow()


    private fun setEpoxyPopularMovieData(movieData: List<Movie>) {
        _epoxyPopularMovieData.update {
            epoxyMapper.epoxyPopularMovieListMapper(epoxyMapper.extractMovieToEpoxy(movieData))
        }
    }


    private fun setEpoxyPopularMovieLoading() {
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

    private fun setEpoxyPopularMovieError() {
        _epoxyPopularMovieData.update {
            mutableListOf(EpoxyPopularMovieData.Error)
        }
    }


    private fun setEpoxyNowPlayingMovieData(movieData: List<Movie>) {
        _epoxyNowPlayingMovieData.update {
            epoxyMapper.epoxyNowPlayingMovieListMapper(epoxyMapper.extractMovieToEpoxy(movieData))
        }
    }


    private fun setEpoxyNowPlayingMovieLoading() {
        _epoxyNowPlayingMovieData.update {
            mutableListOf(
                EpoxyNowPlayingMovieData.Shimmer(0),
                EpoxyNowPlayingMovieData.Shimmer(1),
                EpoxyNowPlayingMovieData.Shimmer(2),
                EpoxyNowPlayingMovieData.Shimmer(3),
                EpoxyNowPlayingMovieData.Shimmer(4),
                EpoxyNowPlayingMovieData.Shimmer(5),
                EpoxyNowPlayingMovieData.Shimmer(6),
            )
        }
    }

    private fun setEpoxyNowPlayingMovieError() {
        _epoxyNowPlayingMovieData.update {
            mutableListOf(EpoxyNowPlayingMovieData.Error)
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
    }
}
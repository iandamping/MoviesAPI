package com.ian.app.muviepedia.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.core.presentation.epoxyMapper.detail.EpoxyDetailScreenSetter
import com.ian.app.muviepedia.feature.detail.enums.DetailFlag
import com.ian.app.muviepedia.feature.state.DetailDataUiState
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
    private val epoxyDetailScreenSetter: EpoxyDetailScreenSetter
) :
    ViewModel() {
    private val _detailDataUiState: MutableStateFlow<DetailDataUiState> = MutableStateFlow(
        DetailDataUiState.initialize()
    )
    val detailDataUiState: StateFlow<DetailDataUiState> get() = _detailDataUiState.asStateFlow()

    private fun CoroutineScope.fetchMovie(id: Int) {
        launch {
            movieRepository.fetchDetailMovie(movieId = id).onStart {
                setLoadingFetchMovieDetail()
                setLoadingFetchCompany()
            }
                .onEach { data ->
                    when (data) {
                        is DomainSource.Error -> {
                            setErrorFetchMovieDetail(data.message)
                            setErrorMovieFetchCompany(data.message)
                        }

                        is DomainSource.Success -> {
                            setSuccessFetchMovieDetail(data.data)
                            setSuccessFetchMovieCompany(data.data.productionCompanies)
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun CoroutineScope.fetchSimilarMovie(id: Int) {
        launch {
            movieRepository.fetchSimilarMovie(movieId = id).onStart {
                _detailDataUiState.update { detailSimilarDataUiState ->
                    detailSimilarDataUiState.copy(similarData = epoxyDetailScreenSetter.setEpoxyDetailSimilarLoading())
                }
            }
                .onEach { data ->
                    when (data) {
                        is DomainSource.Error -> _detailDataUiState.update { uiState ->
                            uiState.copy(
                                similarData = epoxyDetailScreenSetter.setEpoxyDetailSimilarError()
                            )
                        }

                        is DomainSource.Success -> _detailDataUiState.update { uiState ->
                            uiState.copy(
                                similarData = epoxyDetailScreenSetter.setEpoxyDetailMovieSimilarData(
                                    data.data
                                )
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun CoroutineScope.fetchTelevision(id: Int) {
        launch {
            televisionRepository.fetchDetailTv(tvID = id).onStart {
                setLoadingFetchTelevisionDetail()
                setLoadingFetchCompany()
            }
                .onEach { data ->
                    when (data) {
                        is DomainSource.Error -> {
                            setErrorFetchTelevisionDetail(data.message)
                            setErrorTelevisionFetchCompany(data.message)
                        }

                        is DomainSource.Success -> {
                            setSuccessFetchTelevisionDetail(data.data)
                            setSuccessFetchTelevisionCompany(data.data.productionCompanies)
                        }
                    }
                }
                .launchIn(this)
        }
    }

    private fun setSuccessFetchMovieDetail(
        data: MovieDetail
    ) {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                contentData = epoxyDetailScreenSetter.setEpoxyDetailMovieContentData(data),
                imageData = epoxyDetailScreenSetter.setEpoxyDetailImageContentData(data.backdropPath),

                )
        }
    }

    private fun setSuccessFetchTelevisionDetail(
        data: TelevisionDetail
    ) {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                contentData = epoxyDetailScreenSetter.setEpoxyDetailTelevisionContentData(data),
                imageData = epoxyDetailScreenSetter.setEpoxyDetailImageContentData(data.backdropPath),
            )
        }
    }

    private fun setSuccessFetchTelevisionCompany(
        data: List<TelevisionDetail.ProductionCompany>
    ) {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                companyData = epoxyDetailScreenSetter.setEpoxyDetailTelevisionCompanyData(data),
            )
        }
    }

    private fun setSuccessFetchMovieCompany(
        data: List<MovieDetail.ProductionCompany>
    ) {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                companyData = epoxyDetailScreenSetter.setEpoxyDetailMovieCompanyData(data)
            )
        }
    }

    private fun setErrorFetchMovieDetail(message: String) {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                contentData = epoxyDetailScreenSetter.setEpoxyDetailContentError(message),
                imageData = epoxyDetailScreenSetter.setEpoxyDetailImageError(),

                )
        }
    }

    private fun setErrorFetchTelevisionDetail(message: String) {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                contentData = epoxyDetailScreenSetter.setEpoxyDetailContentError(message),
                imageData = epoxyDetailScreenSetter.setEpoxyDetailImageError()

            )
        }
    }

    private fun setErrorMovieFetchCompany(message: String) {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                companyData = epoxyDetailScreenSetter.setEpoxyDetailCompanyError(message)
            )
        }
    }

    private fun setErrorTelevisionFetchCompany(message: String) {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                companyData = epoxyDetailScreenSetter.setEpoxyDetailCompanyError(message)
            )
        }
    }

    private fun setLoadingFetchMovieDetail() {
        _detailDataUiState.update { detailDataUiState ->
            detailDataUiState.copy(
                contentData = epoxyDetailScreenSetter.setEpoxyDetailContentLoading(),
                imageData = epoxyDetailScreenSetter.setEpoxyDetailImageLoading(),
            )
        }
    }

    private fun setLoadingFetchTelevisionDetail() {
        _detailDataUiState.update { uiState ->
            uiState.copy(
                contentData = epoxyDetailScreenSetter.setEpoxyDetailContentLoading(),
                imageData = epoxyDetailScreenSetter.setEpoxyDetailImageLoading(),

                )
        }
    }

    private fun setLoadingFetchCompany() {
        _detailDataUiState.update { uiState ->
            uiState.copy(companyData = epoxyDetailScreenSetter.setEpoxyDetailCompanyLoading())
        }
    }

    private fun CoroutineScope.fetchSimilarTelevision(id: Int) {
        launch {
            televisionRepository.fetchSimilarTv(tvID = id).onStart {
                _detailDataUiState.update { uiState ->
                    uiState.copy(
                        similarData = epoxyDetailScreenSetter.setEpoxyDetailSimilarLoading()
                    )
                }
            }
                .onEach { data ->
                    when (data) {
                        is DomainSource.Error -> _detailDataUiState.update { uiState ->
                            uiState.copy(
                                similarData = epoxyDetailScreenSetter.setEpoxyDetailSimilarError()
                            )
                        }

                        is DomainSource.Success -> _detailDataUiState.update { uiState ->
                            uiState.copy(
                                similarData = epoxyDetailScreenSetter.setEpoxyDetailTelevisionSimilarData(
                                    data.data
                                )
                            )
                        }
                    }
                }
                .launchIn(this)
        }
    }


    fun getDetailMovie(id: Int, flag: DetailFlag) {
        viewModelScope.launch {
            if (flag == DetailFlag.Movie) {
                fetchMovie(id = id)
                fetchSimilarMovie(id = id)
            } else {
                fetchTelevision(id = id)
                fetchSimilarTelevision(id = id)
            }
        }
    }
}

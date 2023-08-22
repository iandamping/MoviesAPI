package com.ian.app.muviepedia.core.data.repository

import app.cash.turbine.test
import com.ian.app.muviepedia.core.data.dataSource.cache.source.movie.MovieLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.source.movie.MovieRemoteDataSource
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.mapToDomain
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    private lateinit var sut: MovieRepository
    private val remoteDataSource: MovieRemoteDataSource = mockk()
    private val localDataSource: MovieLocalDataSource = mockk()

    @Before
    fun setUp() {
        sut = MovieRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `fetchDetailMovie return Success`() = runTest {
        //arrange
        val mockResponse: DetailMovieResponse = mockk()
        val mock = DataSource.Success(mockResponse)
        val domainMock: MovieDetail = mockk()
        coEvery { remoteDataSource.getDetailMovie(any()) } returns mock
        mockkStatic("com.ian.app.muviepedia.core.data.repository.model.MovieDetailKt")
        every {
            mockResponse.mapToDomain()
        } returns domainMock

        //act
        val result = sut.fetchDetailMovie(1)
        //assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Success(mockResponse.mapToDomain()), state)
            Assert.assertEquals(mockResponse.mapToDomain(), (state as DomainSource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `fetchDetailMovie return Error`() = runTest {
        //arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        coEvery { remoteDataSource.getDetailMovie(any()) } returns mock

        //act
        val result = sut.fetchDetailMovie(1)
        //assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }


}
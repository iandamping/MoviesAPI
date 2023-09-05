package com.ian.app.muviepedia.core.data.repository

import app.cash.turbine.test
import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.source.tv.TvRemoteDataSource
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.data.repository.model.mapToDomain
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvRepositoryImplTest {
    private lateinit var sut: TvRepository
    private val remoteDataSource: TvRemoteDataSource = mockk()
    private val localDataSource: TvLocalDataSource = mockk()

    @Before
    fun setUp() {
        sut = TvRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `fetchDetailTv return Success`() = runTest {
        //arrange
        val mockResponse: DetailTvResponse = mockk()
        val mock = DataSource.Success(mockResponse)
        val domainMock: TelevisionDetail = mockk()
        coEvery { remoteDataSource.getDetailTv(any()) } returns mock
        mockkStatic(DetailTvResponse::mapToDomain)
        every {
            mockResponse.mapToDomain()
        } returns domainMock

        //act
        val result = sut.fetchDetailTv(1)
        //assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Success(mockResponse.mapToDomain()), state)
            Assert.assertEquals(mockResponse.mapToDomain(), (state as DomainSource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `fetchDetailTv return Error`() = runTest {
        //arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        coEvery { remoteDataSource.getDetailTv(any()) } returns mock

        //act
        val result = sut.fetchDetailTv(1)
        //assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

}
package com.ian.app.muviepedia.core.data.repository

import app.cash.turbine.test
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalTvEntity
import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvType
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.source.tv.TvRemoteDataSource
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.repository.model.mapLocalTelevisionListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapRemoteTelevisionListToDomain
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.util.isExpireds
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvRepositoryImplFailureTest {
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
    fun `fetchDetailTv return Error`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        coEvery { remoteDataSource.getDetailTv(any()) } returns mock

        // act
        val result = sut.fetchDetailTv(1)
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `fetchSimilarTv return Error`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        coEvery { remoteDataSource.getSimilarTv(any()) } returns mock

        // act
        val result = sut.fetchSimilarTv(1)
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `prefetchPopularTv return Error expired`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")

        coEvery { remoteDataSource.getPopularTv() } returns mock
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.Popular.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock isNullOrEmpty for shouldFetch()
        every { isExpireds(any()) } returns true

        // act
        val result = sut.fetchPopularTv()
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `prefetchPopularTv return Error not expired`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)

        coEvery { remoteDataSource.getPopularTv() } returns mock
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.Popular.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns emptyList()

        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns false

        // act
        val result = sut.fetchPopularTv()
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `prefetchOnAirTv return Error expired`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")

        coEvery { remoteDataSource.getOnAirTv() } returns mock
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.OnAir.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock isNullOrEmpty for shouldFetch()
        every { isExpireds(any()) } returns true

        // act
        val result = sut.fetchOnAirTv()
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `prefetchOnAirTv return Error not expired`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)

        coEvery { remoteDataSource.getOnAirTv() } returns mock
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.OnAir.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns emptyList()

        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns false

        // act
        val result = sut.fetchOnAirTv()
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `prefetchAiringTodayTv return Error expired`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")

        coEvery { remoteDataSource.getAiringTodayTv() } returns mock
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.AiringToday.name) } returns flowOf(
            domainListLocalMockEntity
        )

        // mock isNullOrEmpty for shouldFetch()
        every { isExpireds(any()) } returns true

        // act
        val result = sut.fetchAiringTodayTv()
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `prefetchAiringTodayTv return Error not expired`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)

        coEvery { remoteDataSource.getAiringTodayTv() } returns mock
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.AiringToday.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns emptyList()

        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns false

        // act
        val result = sut.fetchAiringTodayTv()
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `prefetchTopRatedTv return Error expired`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")

        coEvery { remoteDataSource.getTopRatedTv() } returns mock
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.TopRated.name) } returns flowOf(
            domainListLocalMockEntity
        )

        // mock isNullOrEmpty for shouldFetch()
        every { isExpireds(any()) } returns true

        // act
        val result = sut.fetchTopRatedTv()
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `prefetchTopRatedTv return Error not expired`() = runTest {
        // arrange
        val failedMessage = "error"
        val mock = DataSource.Error(failedMessage)
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)

        coEvery { remoteDataSource.getTopRatedTv() } returns mock
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.TopRated.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns emptyList()

        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns false

        // act
        val result = sut.fetchTopRatedTv()
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Error(failedMessage), state)
            Assert.assertEquals(failedMessage, (state as DomainSource.Error).message)
            awaitComplete()
        }
    }
}

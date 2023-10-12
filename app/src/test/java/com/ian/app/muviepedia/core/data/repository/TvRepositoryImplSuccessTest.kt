package com.ian.app.muviepedia.core.data.repository

import app.cash.turbine.test
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalTvEntity
import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvType
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.source.tv.TvRemoteDataSource
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.data.repository.model.mapLocalTelevisionListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapRemoteTelevisionListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapToDomain
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.util.isExpireds
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvRepositoryImplSuccessTest {
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
        // arrange
        val mockResponse: DetailTvResponse = mockk()
        val mock = DataSource.Success(mockResponse)
        val domainMock: TelevisionDetail = mockk()
        coEvery { remoteDataSource.getDetailTv(any()) } returns mock
        mockkStatic(DetailTvResponse::mapToDomain)
        every {
            mockResponse.mapToDomain()
        } returns domainMock

        // act
        val result = sut.fetchDetailTv(1)
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Success(mockResponse.mapToDomain()), state)
            Assert.assertEquals(mockResponse.mapToDomain(), (state as DomainSource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `fetchSimilarTv return Success`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> = mockk()
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        coEvery { remoteDataSource.getSimilarTv(any()) } returns DataSource.Success(dataResponse)
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.fetchSimilarTv(1)

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(mockResponse.mapRemoteTelevisionListToDomain()),
                state
            )
            Assert.assertEquals(
                mockResponse.mapRemoteTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )
            awaitComplete()
        }
    }

    @Test
    fun `prefetchPopularTv return Success data from cache`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> =
            listOf(TvDataResponse("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getPopularTv() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.Popular.name) } returns flowOf(
            domainListLocalMockEntity
        )
        coJustRun { localDataSource.deleteAllData() }
        coJustRun { localDataSource.insertTvData(any()) }

        every { isExpireds(any()) } returns false

        // mock remote data in saveCallResult()
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.prefetchPopularTv()

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())

        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        )

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalTelevisionListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `prefetchPopularTv return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> =
            listOf(TvDataResponse("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getPopularTv() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.Popular.name) } returns flowOf(
            domainListLocalMockEntity
        )
        coJustRun { localDataSource.deleteAllData() }
        coJustRun { localDataSource.insertTvData(any()) }

        every { isExpireds(any()) } returns true

        // mock remote data in saveCallResult()
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.prefetchPopularTv()

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        )
        Assert.assertTrue(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalTelevisionListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `prefetchOnAirTv return Success data from cache`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> =
            listOf(TvDataResponse("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getOnAirTv() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.OnAir.name) } returns flowOf(
            domainListLocalMockEntity
        )
        coJustRun { localDataSource.deleteAllData() }
        coJustRun { localDataSource.insertTvData(any()) }

        every { isExpireds(any()) } returns false

        // mock remote data in saveCallResult()
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.prefetchOnAirTv()

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())

        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        )

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalTelevisionListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `prefetchOnAirTv return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> =
            listOf(TvDataResponse("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getOnAirTv() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.OnAir.name) } returns flowOf(
            domainListLocalMockEntity
        )
        coJustRun { localDataSource.deleteAllData() }
        coJustRun { localDataSource.insertTvData(any()) }

        every { isExpireds(any()) } returns true

        // mock remote data in saveCallResult()
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.prefetchOnAirTv()

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        )
        Assert.assertTrue(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalTelevisionListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `prefetchAiringTodayTv return Success data from cache`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> =
            listOf(TvDataResponse("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getAiringTodayTv() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.AiringToday.name) } returns flowOf(
            domainListLocalMockEntity
        )
        coJustRun { localDataSource.deleteAllData() }
        coJustRun { localDataSource.insertTvData(any()) }

        every { isExpireds(any()) } returns false

        // mock remote data in saveCallResult()
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.prefetchAiringTodayTv()

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())

        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        )

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalTelevisionListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `prefetchAiringTodayTv return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> =
            listOf(TvDataResponse("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getAiringTodayTv() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.AiringToday.name) } returns flowOf(
            domainListLocalMockEntity
        )
        coJustRun { localDataSource.deleteAllData() }
        coJustRun { localDataSource.insertTvData(any()) }

        every { isExpireds(any()) } returns true

        // mock remote data in saveCallResult()
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.prefetchAiringTodayTv()

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        )
        Assert.assertTrue(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalTelevisionListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `prefetchTopRatedTv return Success data from cache`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> =
            listOf(TvDataResponse("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getTopRatedTv() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.TopRated.name) } returns flowOf(
            domainListLocalMockEntity
        )
        coJustRun { localDataSource.deleteAllData() }
        coJustRun { localDataSource.insertTvData(any()) }

        every { isExpireds(any()) } returns false

        // mock remote data in saveCallResult()
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.prefetchTopRatedTv()

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())

        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        )

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalTelevisionListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `prefetchTopRatedTv return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<TvDataResponse> =
            listOf(TvDataResponse("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val domainListLocalMockEntity: List<LocalTvEntity> =
            listOf(LocalTvEntity(1, 1, "a", "a", "a", "a", 1.1, 1, "a", "a", "a", 1.1, "a", 1L))

        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<TvDataResponse>::mapRemoteTelevisionListToDomain)
        mockkStatic(List<LocalTvEntity>::mapLocalTelevisionListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getTopRatedTv() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllTvDataByType(TvType.TopRated.name) } returns flowOf(
            domainListLocalMockEntity
        )
        coJustRun { localDataSource.deleteAllData() }
        coJustRun { localDataSource.insertTvData(any()) }

        every { isExpireds(any()) } returns true

        // mock remote data in saveCallResult()
        every {
            mockResponse.mapRemoteTelevisionListToDomain()
        } returns domainMockResponse
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.prefetchTopRatedTv()

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteTelevisionListToDomain())
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalTelevisionListToDomain()
        )
        Assert.assertTrue(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalTelevisionListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalTelevisionListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }
}

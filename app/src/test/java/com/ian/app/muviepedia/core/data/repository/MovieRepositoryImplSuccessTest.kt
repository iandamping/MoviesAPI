package com.ian.app.muviepedia.core.data.repository

import app.cash.turbine.test
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalMovieEntity
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.mapListToDomain
import com.ian.app.muviepedia.core.data.dataSource.cache.source.movie.MovieLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.cache.source.movie.MovieType
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.source.movie.MovieRemoteDataSource
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.mapLocalMovieListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapRemoteMovieListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapToDomain
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.util.isExpireds
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryImplSuccessTest {

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
        // arrange
        val mockResponse: DetailMovieResponse = mockk()
        val mock = DataSource.Success(mockResponse)
        val domainMock: MovieDetail = mockk()
        coEvery { remoteDataSource.getDetailMovie(any()) } returns mock
        mockkStatic(DetailMovieResponse::mapToDomain)
        every {
            mockResponse.mapToDomain()
        } returns domainMock

        // act
        val result = sut.fetchDetailMovie(1)
        // assert
        result.test {
            val state = awaitItem()
            Assert.assertEquals(DomainSource.Success(mockResponse.mapToDomain()), state)
            Assert.assertEquals(mockResponse.mapToDomain(), (state as DomainSource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `fetchSimilarMovie return Success`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        mockkStatic(List<MovieDataResponse>::mapRemoteMovieListToDomain)
        coEvery { remoteDataSource.getSimilarMovie(any()) } returns DataSource.Success(dataResponse)
        every {
            mockResponse.mapRemoteMovieListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.fetchSimilarMovie(1)

        // assert
        Assert.assertEquals(domainMockResponse, mockResponse.mapRemoteMovieListToDomain())

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(mockResponse.mapRemoteMovieListToDomain()),
                state
            )
            Assert.assertEquals(
                mockResponse.mapRemoteMovieListToDomain(),
                (state as DomainSource.Success).data
            )
            awaitComplete()
        }
    }

    @Test
    fun `fetchPopularMovie return Success data from cache`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = mockk()

        mockkStatic("kotlin.collections.CollectionsKt")
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getPopularMovie() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllMovieDataByType(MovieType.Popular.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock remote data in saveCallResult()
        every {
            mockResponse.mapListToDomain(any(), any())
        } returns domainListLocalMockEntity
        // mock firstOrNull to check isExpired()
        coEvery { flowOf(domainListLocalMockEntity).firstOrNull() } returns domainListLocalMockEntity
        // mock isNullOrEmpty for shouldFetch()
        every { domainMockResponse.isEmpty() } returns false
        // mock localData to check isExpired()
        every { domainListLocalMockEntity.isEmpty() } returns false
        every { domainListLocalMockEntity.firstOrNull()?.timeStamp } returns 1L
        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns false
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.fetchPopularMovie()

        // assert
        Assert.assertEquals(domainListLocalMockEntity, mockResponse.mapListToDomain("a", 1L))
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        )
        Assert.assertFalse(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchPopularMovie return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = mockk()

        mockkStatic("kotlin.collections.CollectionsKt")
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getPopularMovie() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllMovieDataByType(MovieType.Popular.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock remote data in saveCallResult()
        every {
            mockResponse.mapListToDomain(any(), any())
        } returns domainListLocalMockEntity
        // mock firstOrNull to check isExpired()
        coEvery { flowOf(domainListLocalMockEntity).firstOrNull() } returns domainListLocalMockEntity
        // mock isNullOrEmpty for shouldFetch()
        every { domainMockResponse.isEmpty() } returns false
        // mock localData to check isExpired()
        every { domainListLocalMockEntity.isEmpty() } returns false
        every { domainListLocalMockEntity.firstOrNull()?.timeStamp } returns 1L
        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns true
        // mock clearFirst()
        coJustRun { localDataSource.deleteAll() }
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // mock saveCallResult()
        coJustRun { localDataSource.insertMovie(any()) }

        // act
        val result = sut.fetchPopularMovie()

        // assert
        Assert.assertEquals(domainListLocalMockEntity, mockResponse.mapListToDomain("a", 1L))
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        )
        Assert.assertTrue(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchNowPlayingMovie return Success data from cache`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = mockk()

        mockkStatic("kotlin.collections.CollectionsKt")
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getNowPlaying() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllMovieDataByType(MovieType.NowPlaying.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock remote data in saveCallResult()
        every {
            mockResponse.mapListToDomain(any(), any())
        } returns domainListLocalMockEntity
        // mock firstOrNull to check isExpired()
        coEvery { flowOf(domainListLocalMockEntity).firstOrNull() } returns domainListLocalMockEntity
        // mock isNullOrEmpty for shouldFetch()
        every { domainMockResponse.isEmpty() } returns false
        // mock localData to check isExpired()
        every { domainListLocalMockEntity.isEmpty() } returns false
        every { domainListLocalMockEntity.firstOrNull()?.timeStamp } returns 1L
        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns false
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.fetchNowPlayingMovie()

        // assert
        Assert.assertEquals(domainListLocalMockEntity, mockResponse.mapListToDomain("a", 1L))
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        )
        Assert.assertFalse(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchNowPlayingMovie return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = mockk()

        mockkStatic("kotlin.collections.CollectionsKt")
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getNowPlaying() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllMovieDataByType(MovieType.NowPlaying.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock remote data in saveCallResult()
        every {
            mockResponse.mapListToDomain(any(), any())
        } returns domainListLocalMockEntity
        // mock firstOrNull to check isExpired()
        coEvery { flowOf(domainListLocalMockEntity).firstOrNull() } returns domainListLocalMockEntity
        // mock isNullOrEmpty for shouldFetch()
        every { domainMockResponse.isEmpty() } returns false
        // mock localData to check isExpired()
        every { domainListLocalMockEntity.isEmpty() } returns false
        every { domainListLocalMockEntity.firstOrNull()?.timeStamp } returns 1L
        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns true
        // mock clearFirst()
        coJustRun { localDataSource.deleteAll() }
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // mock saveCallResult()
        coJustRun { localDataSource.insertMovie(any()) }

        // act
        val result = sut.fetchNowPlayingMovie()

        // assert
        Assert.assertEquals(domainListLocalMockEntity, mockResponse.mapListToDomain("a", 1L))
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        )
        Assert.assertTrue(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchTopRatedMovie return Success data from cache`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = mockk()

        mockkStatic("kotlin.collections.CollectionsKt")
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getTopRatedMovie() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllMovieDataByType(MovieType.TopRated.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock remote data in saveCallResult()
        every {
            mockResponse.mapListToDomain(any(), any())
        } returns domainListLocalMockEntity
        // mock firstOrNull to check isExpired()
        coEvery { flowOf(domainListLocalMockEntity).firstOrNull() } returns domainListLocalMockEntity
        // mock isNullOrEmpty for shouldFetch()
        every { domainMockResponse.isEmpty() } returns false
        // mock localData to check isExpired()
        every { domainListLocalMockEntity.isEmpty() } returns false
        every { domainListLocalMockEntity.firstOrNull()?.timeStamp } returns 1L
        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns false
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.fetchTopRatedMovie()

        // assert
        Assert.assertEquals(domainListLocalMockEntity, mockResponse.mapListToDomain("a", 1L))
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        )
        Assert.assertFalse(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchTopRatedMovie return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = mockk()

        mockkStatic("kotlin.collections.CollectionsKt")
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getTopRatedMovie() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllMovieDataByType(MovieType.TopRated.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock remote data in saveCallResult()
        every {
            mockResponse.mapListToDomain(any(), any())
        } returns domainListLocalMockEntity
        // mock firstOrNull to check isExpired()
        coEvery { flowOf(domainListLocalMockEntity).firstOrNull() } returns domainListLocalMockEntity
        // mock isNullOrEmpty for shouldFetch()
        every { domainMockResponse.isEmpty() } returns false
        // mock localData to check isExpired()
        every { domainListLocalMockEntity.isEmpty() } returns false
        every { domainListLocalMockEntity.firstOrNull()?.timeStamp } returns 1L
        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns true
        // mock clearFirst()
        coJustRun { localDataSource.deleteAll() }
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // mock saveCallResult()
        coJustRun { localDataSource.insertMovie(any()) }

        // act
        val result = sut.fetchTopRatedMovie()

        // assert
        Assert.assertEquals(domainListLocalMockEntity, mockResponse.mapListToDomain("a", 1L))
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        )
        Assert.assertTrue(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchUpComingMovie return Success data from cache`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = mockk()

        mockkStatic("kotlin.collections.CollectionsKt")
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getUpComingMovie() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllMovieDataByType(MovieType.UpComing.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock remote data in saveCallResult()
        every {
            mockResponse.mapListToDomain(any(), any())
        } returns domainListLocalMockEntity
        // mock firstOrNull to check isExpired()
        coEvery { flowOf(domainListLocalMockEntity).firstOrNull() } returns domainListLocalMockEntity
        // mock isNullOrEmpty for shouldFetch()
        every { domainMockResponse.isEmpty() } returns false
        // mock localData to check isExpired()
        every { domainListLocalMockEntity.isEmpty() } returns false
        every { domainListLocalMockEntity.firstOrNull()?.timeStamp } returns 1L
        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns false
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.fetchUpComingMovie()

        // assert
        Assert.assertEquals(domainListLocalMockEntity, mockResponse.mapListToDomain("a", 1L))
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        )
        Assert.assertFalse(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchUpComingMovie return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = mockk()

        mockkStatic("kotlin.collections.CollectionsKt")
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for createCall()
        coEvery { remoteDataSource.getUpComingMovie() } returns DataSource.Success(dataResponse)
        // mock setup for loadFromDB() & isExpired()
        every { localDataSource.loadAllMovieDataByType(MovieType.UpComing.name) } returns flowOf(
            domainListLocalMockEntity
        )
        // mock remote data in saveCallResult()
        every {
            mockResponse.mapListToDomain(any(), any())
        } returns domainListLocalMockEntity
        // mock firstOrNull to check isExpired()
        coEvery { flowOf(domainListLocalMockEntity).firstOrNull() } returns domainListLocalMockEntity
        // mock isNullOrEmpty for shouldFetch()
        every { domainMockResponse.isEmpty() } returns false
        // mock localData to check isExpired()
        every { domainListLocalMockEntity.isEmpty() } returns false
        every { domainListLocalMockEntity.firstOrNull()?.timeStamp } returns 1L
        // mock extension func to check isExpired()
        every { isExpireds(any()) } returns true
        // mock clearFirst()
        coJustRun { localDataSource.deleteAll() }
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // mock saveCallResult()
        coJustRun { localDataSource.insertMovie(any()) }

        // act
        val result = sut.fetchUpComingMovie()

        // assert
        Assert.assertEquals(domainListLocalMockEntity, mockResponse.mapListToDomain("a", 1L))
        Assert.assertEquals(
            domainListLocalMockEntity,
            flowOf(domainListLocalMockEntity).firstOrNull()
        )
        Assert.assertEquals(
            domainMockResponse,
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        )
        Assert.assertTrue(isExpireds(1L))

        result.test {
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchSearchMovie return Success data from cache`() = runTest {
        // arrange
        val domainMockResponse: List<Movie> = listOf(
            Movie(1,
                1,
                false,
                1.1,
                "a",
                1.1,
                "a",
                "a",
                "a",
                "a",
                false,
                "a",
                "a",
                ))
        val domainListLocalMockEntity: List<LocalMovieEntity> = listOf(
            LocalMovieEntity(
                1,
                1,
                "a",
                "a",
                "a",
                1,
                false,
                1.1,
                1.1,
                "a",
                "a",
                "a",
                false,
                "a",
                "a",
                1L
            )
        )

        mockkStatic(List<MovieDataResponse>::mapListToDomain)
        mockkStatic(List<LocalMovieEntity>::mapLocalMovieListToDomain)
        // mock setup for loadFromDB()
        every { localDataSource.loadAllMovie() } returns flowOf(domainListLocalMockEntity)
        // mock loadFromDB()
        every {
            domainListLocalMockEntity.mapLocalMovieListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.fetchSearchMovie("a")

        // assert
        result.test {
            // get data from cache
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(domainListLocalMockEntity.mapLocalMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                domainListLocalMockEntity.mapLocalMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }

    @Test
    fun `fetchSearchMovie return Success data from remote`() = runTest {
        // arrange
        val mockResponse: List<MovieDataResponse> = mockk()
        val dataResponse = BaseResponse(1, 1, 1, mockResponse)
        val domainMockResponse: List<Movie> = mockk()
        val domainListLocalMockEntity: List<LocalMovieEntity> = emptyList()

        mockkStatic(List<MovieDataResponse>::mapRemoteMovieListToDomain)
        mockkStatic("kotlin.collections.CollectionsKt")
        // mock setup for loadFromDB()
        every { localDataSource.loadAllMovie() } returns flowOf(domainListLocalMockEntity)
        // mock remoteResponse
        coEvery {
            remoteDataSource.searchMovie(any())
        } returns DataSource.Success(dataResponse)

        every {
            mockResponse.mapRemoteMovieListToDomain()
        } returns domainMockResponse

        // act
        val result = sut.fetchSearchMovie("a")

        // assert
        result.test {
            // get data from remote
            val state = awaitItem()
            Assert.assertEquals(
                DomainSource.Success(mockResponse.mapRemoteMovieListToDomain()),
                state
            )

            Assert.assertEquals(
                mockResponse.mapRemoteMovieListToDomain(),
                (state as DomainSource.Success).data
            )

            awaitComplete()
        }
    }
}

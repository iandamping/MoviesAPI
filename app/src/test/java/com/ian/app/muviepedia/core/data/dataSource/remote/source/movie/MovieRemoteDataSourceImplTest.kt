package com.ian.app.muviepedia.core.data.dataSource.remote.source.movie

import com.ian.app.muviepedia.core.data.dataSource.remote.api.ApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.core.data.model.RemoteResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieRemoteDataSourceImplTest {

    private lateinit var sut: MovieRemoteDataSource
    private val api: ApiInterface = mockk()
    private val remoteHelper: RemoteHelper = mockk()


    @Before
    fun setUp() {
        sut = MovieRemoteDataSourceImpl(api = api, injectedRemoteHelper = remoteHelper)
    }

    @Test
    fun `getDetailMovie return success`() = runTest {
        //arrange
        val mockData: DetailMovieResponse = mockk()
        successDetailMovieResponse(mockData)
        //act
        val result = sut.getDetailMovie(1)
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getDetailMovie return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedDetailMovieResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getDetailMovie(1)
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getSimilarMovie return success`() = runTest {
        //arrange
        val baseData: List<MovieDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successSimilarMovieDataResponse(mockData)
        //act
        val result = sut.getSimilarMovie(1)
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }


    @Test
    fun `getSimilarMovie return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedSimilarMovieDataResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getSimilarMovie(1)
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getPopularMovie return success`() = runTest {
        //arrange
        val baseData: List<MovieDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successPopularMovieDataResponse(mockData)
        //act
        val result = sut.getPopularMovie()
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getPopularMovie return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedPopularMovieDataResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getPopularMovie()
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getUpComingMovie return success`() = runTest {
        //arrange
        val baseData: List<MovieDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successUpComingMovieDataResponse(mockData)
        //act
        val result = sut.getUpComingMovie()
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getUpComingMovie return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedUpComingMovieDataResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getUpComingMovie()
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getNowPlaying return success`() = runTest {
        //arrange
        val baseData: List<MovieDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successNowPlayingMovieDataResponse(mockData)
        //act
        val result = sut.getNowPlaying()
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getNowPlaying return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedNowPlayingMovieDataResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getNowPlaying()
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getTopRatedMovie return success`() = runTest {
        //arrange
        val baseData: List<MovieDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successTopRatedMovieDataResponse(mockData)
        //act
        val result = sut.getTopRatedMovie()
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getTopRatedMovie return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedTopRatedMovieDataResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getTopRatedMovie()
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `searchMovie return success`() = runTest {
        //arrange
        val baseData: List<MovieDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successSearchMovieDataResponse(mockData)
        //act
        val result = sut.searchMovie("a")
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `searchMovie return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedSearchMovieDataResponse(RuntimeException(errorMessage))
        //act
        val result = sut.searchMovie("a")
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }


    private suspend fun successDetailMovieResponse(mockData: DetailMovieResponse) = runTest {
        coEvery {
            remoteHelper.remoteCall(
                api.getDetailMovieAsync(
                    any(),
                    any()
                )
            )
        } returns RemoteResult.Success(
            Response.success(mockData)
        )
    }

    private suspend fun failedDetailMovieResponse(exception: Exception) = runTest {
        coEvery {
            remoteHelper.remoteCall(
                api.getDetailMovieAsync(
                    any(),
                    any()
                )
            )
        } returns RemoteResult.Error(
            exception
        )
    }

    private suspend fun successSimilarMovieDataResponse(mockData: BaseResponse<MovieDataResponse>) =
        runTest {
            coEvery {
                remoteHelper.remoteWithBaseCall(
                    api.getSimilarMovieAsync(
                        any(),
                        any()
                    )
                )
            } returns RemoteBaseResult.Success(
                Response.success(mockData)
            )
        }

    private suspend fun failedSimilarMovieDataResponse(exception: Exception) = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getSimilarMovieAsync(
                    any(),
                    any()
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }

    private suspend fun successPopularMovieDataResponse(mockData: BaseResponse<MovieDataResponse>) =
        runTest {
            coEvery {
                remoteHelper.remoteWithBaseCall(
                    api.getPopularMovieAsync(
                        any(),
                    )
                )
            } returns RemoteBaseResult.Success(
                Response.success(mockData)
            )
        }

    private suspend fun failedPopularMovieDataResponse(exception: Exception) = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getPopularMovieAsync(
                    any(),
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }

    private suspend fun successUpComingMovieDataResponse(mockData: BaseResponse<MovieDataResponse>) =
        runTest {
            coEvery {
                remoteHelper.remoteWithBaseCall(
                    api.getUpComingMovieAsync(
                        any(),
                    )
                )
            } returns RemoteBaseResult.Success(
                Response.success(mockData)
            )
        }

    private suspend fun failedUpComingMovieDataResponse(exception: Exception) = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getUpComingMovieAsync(
                    any(),
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }

    private suspend fun successNowPlayingMovieDataResponse(mockData: BaseResponse<MovieDataResponse>) =
        runTest {
            coEvery {
                remoteHelper.remoteWithBaseCall(
                    api.getNowPlayingMovieAsync(
                        any(),
                    )
                )
            } returns RemoteBaseResult.Success(
                Response.success(mockData)
            )
        }

    private suspend fun failedNowPlayingMovieDataResponse(exception: Exception) = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getNowPlayingMovieAsync(
                    any(),
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }

    private suspend fun successTopRatedMovieDataResponse(mockData: BaseResponse<MovieDataResponse>) =
        runTest {
            coEvery {
                remoteHelper.remoteWithBaseCall(
                    api.getTopRatedMovieAsync(
                        any(),
                    )
                )
            } returns RemoteBaseResult.Success(
                Response.success(mockData)
            )
        }

    private suspend fun failedTopRatedMovieDataResponse(exception: Exception) = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getTopRatedMovieAsync(
                    any(),
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }

    private suspend fun successSearchMovieDataResponse(mockData: BaseResponse<MovieDataResponse>) =
        runTest {
            coEvery {
                remoteHelper.remoteWithBaseCall(
                    api.getSearchMovieResponse(
                        any(),
                        any(),
                        any(),
                    )
                )
            } returns RemoteBaseResult.Success(
                Response.success(mockData)
            )
        }

    private suspend fun failedSearchMovieDataResponse(exception: Exception) = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getSearchMovieResponse(
                    any(),
                    any(),
                    any(),
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }
}
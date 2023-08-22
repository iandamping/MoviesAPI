package com.ian.app.muviepedia.core.data.dataSource.remote.source.tv

import com.ian.app.muviepedia.core.data.dataSource.remote.api.ApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.helper.RemoteHelper
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailTvResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
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
class TvRemoteDataSourceImplTest {

    private lateinit var sut: TvRemoteDataSource
    private val api: ApiInterface = mockk()
    private val remoteHelper: RemoteHelper = mockk()


    @Before
    fun setUp() {
        sut = TvRemoteDataSourceImpl(api = api, injectedRemoteHelper = remoteHelper)
    }

    @Test
    fun `getDetailTv return success`() = runTest {
        //arrange
        val mockData: DetailTvResponse = mockk()
        successDetailTelevisionResponse(mockData)
        //act
        val result = sut.getDetailTv(1)
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getDetailTv return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedDetailTelevisionResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getDetailTv(1)
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getSimilarTv return success`() = runTest {
        //arrange
        val baseData: List<TvDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successSimilarTelevisionResponse(mockData)
        //act
        val result = sut.getSimilarTv(1)
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getSimilarTv return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedSimilarTelevisionResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getSimilarTv(1)
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getPopularTv return success`() = runTest {
        //arrange
        val baseData: List<TvDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successPopularTelevisionResponse(mockData)
        //act
        val result = sut.getPopularTv()
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getPopularTv return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedPopularTelevisionResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getPopularTv()
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getTopRatedTv return success`() = runTest {
        //arrange
        val baseData: List<TvDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successTopRatedTelevisionResponse(mockData)
        //act
        val result = sut.getTopRatedTv()
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getTopRatedTv return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedTopRatedTelevisionResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getTopRatedTv()
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }

    @Test
    fun `getAiringTodayTv return success`() = runTest {
        //arrange
        val baseData: List<TvDataResponse> = mockk()
        val mockData = BaseResponse(1, 1, 1, baseData)
        successAiringTelevisionResponse(mockData)
        //act
        val result = sut.getAiringTodayTv()
        //assert
        Assert.assertEquals(DataSource.Success(mockData), result)
        Assert.assertEquals(mockData, (result as DataSource.Success).data)
    }

    @Test
    fun `getAiringTodayTv return failed`() = runTest {
        //arrange
        val errorMessage = "error"
        failedAiringTelevisionResponse(RuntimeException(errorMessage))
        //act
        val result = sut.getAiringTodayTv()
        //assert
        Assert.assertEquals(DataSource.Error(errorMessage), result)
        Assert.assertEquals(errorMessage, (result as DataSource.Error).message)
    }


    private fun successDetailTelevisionResponse(mockData: DetailTvResponse) {
        coEvery {
            remoteHelper.remoteCall(
                api.getDetailTvAsync(
                    any(),
                    any()
                )
            )
        } returns RemoteResult.Success(
            Response.success(mockData)
        )

    }

    private fun failedDetailTelevisionResponse(exception: RuntimeException) {
        coEvery {
            remoteHelper.remoteCall(
                api.getDetailTvAsync(
                    any(),
                    any()
                )
            )
        } returns RemoteResult.Error(
            exception
        )
    }

    private fun successSimilarTelevisionResponse(mockData: BaseResponse<TvDataResponse>) =  runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getSimilarTvAsync(
                    any(),
                    any()
                )
            )
        } returns RemoteBaseResult.Success(
            Response.success(mockData)
        )
    }

    private fun failedSimilarTelevisionResponse(exception: RuntimeException)  = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getSimilarTvAsync(
                    any(),
                    any()
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }


    private fun successPopularTelevisionResponse(mockData: BaseResponse<TvDataResponse>) =  runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getPopularTvAsync(
                    any()
                )
            )
        } returns RemoteBaseResult.Success(
            Response.success(mockData)
        )
    }

    private fun failedPopularTelevisionResponse(exception: RuntimeException)  = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getPopularTvAsync(
                    any()
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }


    private fun successTopRatedTelevisionResponse(mockData: BaseResponse<TvDataResponse>) =  runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getTopRatedTvAsync(
                    any()
                )
            )
        } returns RemoteBaseResult.Success(
            Response.success(mockData)
        )
    }

    private fun failedTopRatedTelevisionResponse(exception: RuntimeException)  = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getTopRatedTvAsync(
                    any()
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }

    private fun successAiringTelevisionResponse(mockData: BaseResponse<TvDataResponse>) =  runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getAiringTodayTvAsync(
                    any()
                )
            )
        } returns RemoteBaseResult.Success(
            Response.success(mockData)
        )
    }

    private fun failedAiringTelevisionResponse(exception: RuntimeException)  = runTest {
        coEvery {
            remoteHelper.remoteWithBaseCall(
                api.getAiringTodayTvAsync(
                    any()
                )
            )
        } returns RemoteBaseResult.Error(
            exception
        )
    }

}
package com.ian.app.muviepedia.core.data.dataSource.remote.helper

import com.ian.app.muviepedia.core.data.dataSource.remote.api.ApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.DetailMovieResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.model.RemoteBaseResult
import com.ian.app.muviepedia.core.data.model.RemoteResult
import com.ian.app.muviepedia.util.UtilityHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteHelperImplTest {

    private lateinit var sut: RemoteHelper
    private val utilityHelper: UtilityHelper = mockk()
    private val api: ApiInterface = mockk()

    @Before
    fun setUp() {
        sut = RemoteHelperImpl(utilityHelper)
    }

    @Test
    fun `any remoteCall should only call at least one api`() = runTest {
        //arrange
        val mockResponse: DetailMovieResponse = mockk()
        val expected = Response.success(mockResponse)
        coEvery { api.getDetailMovieAsync(any(), any()) } returns expected
        //act
        val result = sut.remoteCall(api.getDetailMovieAsync(1, "a"))
        //assert
        coVerify(atLeast = 1) { api.getDetailMovieAsync(any(), any()) }
        assertEquals(RemoteResult.Success(expected), result)
        assertEquals(expected, (result as RemoteResult.Success).data)
    }

    @Test
    fun `any remoteWithBaseCall should only call at least one api`() = runTest {
        //arrange
        val mockResponse: List<TvDataResponse> = mockk()
        val baseMockResponse: BaseResponse<TvDataResponse> = BaseResponse(1, 1, 1, mockResponse)
        val expected = Response.success(baseMockResponse)
        coEvery { api.getPopularTvAsync(any()) } returns expected
        //act
        val result = sut.remoteWithBaseCall(api.getPopularTvAsync("1"))
        //assert
        coVerify(atLeast = 1) { api.getPopularTvAsync(any()) }
        assertEquals(RemoteBaseResult.Success(expected), result)
        assertEquals(expected, (result as RemoteBaseResult.Success).data)
    }
}
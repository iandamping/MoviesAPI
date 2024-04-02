package com.ian.app.muviepedia.core.data.dataSource.cache.source.tv

import com.ian.app.muviepedia.core.data.dataSource.cache.db.dao.TvDao
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalTvEntity
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvLocalDataSourceImplTest {

    private lateinit var sut: TvLocalDataSource
    private val dao: TvDao = mockk()

    @Before
    fun setUp() {
        sut = TvLocalDataSourceImpl(dao)
    }

    @Test
    fun loadAllTvData() {
        val data: Flow<List<LocalTvEntity>> = mockk()
        coEvery { dao.loadAllTvData() } returns data
        val result = sut.loadAllTvData()
        coVerify { dao.loadAllTvData() }
        Assert.assertEquals(data, result)
    }

    @Test
    fun loadAllTvDataById() {
        val data: Flow<LocalTvEntity> = mockk()
        coEvery { dao.loadAllTvDataById(any()) } returns data
        val result = sut.loadAllTvDataById(1)
        coVerify { dao.loadAllTvDataById(any()) }
        Assert.assertEquals(data, result)
    }

    @Test
    fun loadAllTvDataByType() {
        val data: Flow<List<LocalTvEntity>> = mockk()
        coEvery { dao.loadAllTvDataByType(any()) } returns data
        val result = sut.loadAllTvDataByType("a")
        coVerify { dao.loadAllTvDataByType(any()) }
        Assert.assertEquals(data, result)
    }

    @Test
    fun insertTvData() = runTest {
        val data: List<LocalTvEntity> = mockk()
        coJustRun { dao.insertTvData(any()) }
        sut.insertTvData(data)
        coVerify { dao.insertTvData(any()) }
    }

    @Test
    fun deleteAllData() = runTest {
        coJustRun { dao.deleteAllData() }
        sut.deleteAllData()
        coVerify { dao.deleteAllData() }
    }

    @Test
    fun deleteSelectedId() = runTest {
        coJustRun { dao.deleteSelectedId(any()) }
        sut.deleteSelectedId(1)
        coVerify { dao.deleteSelectedId(any()) }
    }
}

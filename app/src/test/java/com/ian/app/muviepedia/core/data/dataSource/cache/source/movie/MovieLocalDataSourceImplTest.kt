package com.ian.app.muviepedia.core.data.dataSource.cache.source.movie

import com.ian.app.muviepedia.core.data.dataSource.cache.db.dao.MovieDao
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalMovieEntity
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieLocalDataSourceImplTest {

    private lateinit var sut: MovieLocalDataSource
    private val dao: MovieDao = mockk()

    @Before
    fun setUp() {
        sut = MovieLocalDataSourceImpl(dao)
    }

    @Test
    fun loadAllMovie() {
        val data: Flow<List<LocalMovieEntity>> = mockk()
        coEvery { dao.loadAllMovieData() } returns data
        val result = sut.loadAllMovie()
        coVerify { dao.loadAllMovieData() }
        Assert.assertEquals(data, result)
    }

    @Test
    fun loadMovieById() {
        val data: Flow<LocalMovieEntity> = mockk()
        coEvery { dao.loadAllMovieDataById(any()) } returns data
        val result = sut.loadMovieById(1)
        coVerify { dao.loadAllMovieDataById(any()) }
        Assert.assertEquals(data, result)
    }

    @Test
    fun loadAllMovieDataByType() {
        val data: Flow<List<LocalMovieEntity>> = mockk()
        coEvery { dao.loadAllMovieDataByType(any()) } returns data
        val result = sut.loadAllMovieDataByType("a")
        coVerify { dao.loadAllMovieDataByType(any()) }
        Assert.assertEquals(data, result)
    }

    @Test
    fun loadAllMovieDataByTitle() {
        val data: Flow<List<LocalMovieEntity>> = mockk()
        coEvery { dao.loadAllMovieDataByTitle(any()) } returns data
        val result = sut.loadAllMovieDataByTitle("a")
        coVerify { dao.loadAllMovieDataByTitle(any()) }
        Assert.assertEquals(data, result)
    }

    @Test
    fun insertMovie() = runTest {
        val data: List<LocalMovieEntity> = mockk()
        coJustRun { dao.insertMovieData(any()) }
        sut.insertMovie(inputMovie = data)
        coVerify { dao.insertMovieData(any()) }
    }

    @Test
    fun deleteAll() = runTest {
        coJustRun { dao.deleteAllData() }
        sut.deleteAll()
        coVerify { dao.deleteAllData() }
    }

    @Test
    fun deleteSelectedId() = runTest {
        coJustRun { dao.deleteSelectedId(any()) }
        sut.deleteSelectedId(1)
        coVerify { dao.deleteSelectedId(any()) }
    }

}
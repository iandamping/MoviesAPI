package com.ian.app.muviepedia.core.data.dataSource.cache.source.movie

import com.ian.app.muviepedia.core.data.dataSource.cache.db.dao.MovieDao
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalMovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) :
    MovieLocalDataSource {

    override fun loadAllMovie(): Flow<List<LocalMovieEntity>> {
        return movieDao.loadAllMovieData()
    }

    override fun loadMovieById(id: Int): Flow<LocalMovieEntity> {
        return movieDao.loadAllMovieDataById(id)
    }

    override fun loadAllMovieDataByType(type: String): Flow<List<LocalMovieEntity>> {
        return movieDao.loadAllMovieDataByType(type)
    }

    override fun insertMovie(vararg inputMovie: LocalMovieEntity) {
        movieDao.insertMovieData(inputMovie = inputMovie)
    }

    override fun deleteAll() {
        movieDao.deleteAllData()
    }

    override fun deleteSelectedId(selectedId: Int) {
        movieDao.deleteSelectedId(selectedId = selectedId)
    }
}
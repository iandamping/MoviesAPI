package com.ian.app.muviepedia.core.data.dataSource.cache.source.movie

import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalMovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun loadAllMovie(): Flow<List<LocalMovieEntity>>

    fun loadMovieById(id: Int): Flow<LocalMovieEntity>

    fun loadAllMovieDataByType(type: String): Flow<List<LocalMovieEntity>>

    fun loadAllMovieDataByTitle(title: String): Flow<List<LocalMovieEntity>>

    suspend fun insertMovie(inputMovie: List<LocalMovieEntity>)

    suspend fun deleteAll()

    suspend fun deleteSelectedId(selectedId: Int)
}
package com.ian.app.muviepedia.core.data.cache.source

import com.ian.app.muviepedia.core.data.cache.db.entity.LocalMovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun loadAllMovie(): Flow<List<LocalMovieEntity>>

    fun loadMovieById(id: Int): Flow<LocalMovieEntity>

    fun insertMovie(vararg inputMovie: LocalMovieEntity)

    fun deleteAll()

    fun deleteSelectedId(selectedId: Int)
}
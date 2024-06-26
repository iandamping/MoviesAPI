package com.ian.app.muviepedia.core.data.dataSource.cache.source.tv

import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalTvEntity
import kotlinx.coroutines.flow.Flow

interface TvLocalDataSource {

    fun loadAllTvData(): Flow<List<LocalTvEntity>>

    fun loadAllTvDataById(id: Int): Flow<LocalTvEntity>

    fun loadAllTvDataByType(type: String): Flow<List<LocalTvEntity>>

    suspend fun insertTvData(inputTv: List<LocalTvEntity>)

    suspend fun deleteAllData()

    suspend fun deleteSelectedId(selectedId: Int)
}

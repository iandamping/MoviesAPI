package com.ian.app.muviepedia.core.data.cache.source.tv

import com.ian.app.muviepedia.core.data.cache.db.entity.LocalTvEntity
import kotlinx.coroutines.flow.Flow

interface TvLocalDataSource {

    fun loadAllTvData(): Flow<List<LocalTvEntity>>

    fun loadAllTvDataById(id: Int): Flow<LocalTvEntity>

    fun insertTvData(vararg inputTv: LocalTvEntity)

    fun deleteAllData()

    fun deleteSelectedId(selectedId: Int)
}
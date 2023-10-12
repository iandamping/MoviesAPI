package com.ian.app.muviepedia.core.data.dataSource.cache.source.tv

import com.ian.app.muviepedia.core.data.dataSource.cache.db.dao.TvDao
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalTvEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvLocalDataSourceImpl @Inject constructor(private val tvDao: TvDao) : TvLocalDataSource {
    override fun loadAllTvData(): Flow<List<LocalTvEntity>> {
        return tvDao.loadAllTvData()
    }

    override fun loadAllTvDataById(id: Int): Flow<LocalTvEntity> {
        return tvDao.loadAllTvDataById(id)
    }

    override fun loadAllTvDataByType(type: String): Flow<List<LocalTvEntity>> {
        return tvDao.loadAllTvDataByType(type)
    }

    override suspend fun insertTvData(inputTv: List<LocalTvEntity>) {
        tvDao.insertTvData(inputTv = inputTv)
    }

    override suspend fun deleteAllData() {
        tvDao.deleteAllData()
    }

    override suspend fun deleteSelectedId(selectedId: Int) {
        tvDao.deleteSelectedId(selectedId)
    }
}

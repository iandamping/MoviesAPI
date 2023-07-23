package com.ian.app.muviepedia.core.data.cache.source.tv

import com.ian.app.muviepedia.core.data.cache.db.dao.TvDao
import com.ian.app.muviepedia.core.data.cache.db.entity.LocalTvEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvLocalDataSourceImpl @Inject constructor(private val tvDao: TvDao) : TvLocalDataSource {
    override fun loadAllTvData(): Flow<List<LocalTvEntity>> {
        return tvDao.loadAllTvData()
    }

    override fun loadAllTvDataById(id: Int): Flow<LocalTvEntity> {
        return tvDao.loadAllTvDataById(id)
    }

    override fun insertTvData(vararg inputTv: LocalTvEntity) {
        tvDao.insertTvData(inputTv = inputTv)
    }

    override fun deleteAllData() {
        tvDao.deleteAllData()
    }

    override fun deleteSelectedId(selectedId: Int) {
        tvDao.deleteSelectedId(selectedId)
    }
}
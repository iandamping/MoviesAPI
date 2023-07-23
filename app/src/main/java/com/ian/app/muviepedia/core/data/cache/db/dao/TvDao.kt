package com.ian.app.muviepedia.core.data.cache.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.core.data.cache.db.entity.LocalTvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {

    @Query("SELECT * FROM tv_entity")
    fun loadAllTvData(): Flow<List<LocalTvEntity>>

    @Query("SELECT * FROM tv_entity WHERE localID = :id")
    fun loadAllTvDataById(id: Int): Flow<LocalTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvData(vararg inputTv: LocalTvEntity)

    @Query("DELETE FROM tv_entity")
    fun deleteAllData()

    @Query("DELETE FROM tv_entity where localID = :selectedId")
    fun deleteSelectedId(selectedId: Int)
}
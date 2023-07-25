package com.ian.app.muviepedia.core.data.dataSource.cache.db.dao

import androidx.room.*
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_entity")
    fun loadAllMovieData(): Flow<List<LocalMovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE localID = :id")
    fun loadAllMovieDataById(id: Int): Flow<LocalMovieEntity>

    @Query("SELECT * FROM movie_entity WHERE movieType = :type")
    fun loadAllMovieDataByType(type: String): Flow<List<LocalMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieData(vararg inputMovie: LocalMovieEntity)

    @Query("DELETE FROM movie_entity")
    fun deleteAllData()

    @Query("DELETE FROM movie_entity where localID = :selectedId")
    fun deleteSelectedId(selectedId: Int)
}
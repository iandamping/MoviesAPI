package com.ian.app.muviepedia.core.data.cache.db.dao

import androidx.room.*
import com.ian.app.muviepedia.core.data.cache.db.entity.LocalMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_entity")
    fun loadAllMovieData(): Flow<List<LocalMovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE localID = :id")
    fun loadAllMovieDataById(id: Int): Flow<LocalMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieData(vararg inputMovie: LocalMovieEntity)

    @Query("DELETE FROM movie_entity")
    fun deleteAllData()

    @Query("DELETE FROM movie_entity where localID = :selectedId")
    fun deleteSelectedId(selectedId: Int)
}
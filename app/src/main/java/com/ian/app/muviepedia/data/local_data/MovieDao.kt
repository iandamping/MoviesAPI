package com.ian.app.muviepedia.data.local_data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *
Created by Ian Damping on 05/06/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_data")
    fun loadAllMovieData(): LiveData<List<LocalMovieData>>

    @Query("SELECT * FROM movie_data WHERE localID = :id")
    fun loadAllMovieDataById(id: Int?): LiveData<LocalMovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieData(inputMovie: LocalMovieData?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovieData(updateMovie: LocalMovieData?)

    @Query("DELETE FROM movie_data")
    fun deleteAllData()

    @Query("DELETE FROM movie_data where localID = :selectedId")
    fun deleteSelectedId(selectedId: Int)
}
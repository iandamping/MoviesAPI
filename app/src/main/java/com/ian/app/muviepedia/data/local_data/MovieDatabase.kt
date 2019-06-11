package com.ian.app.muviepedia.data.local_data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *
Created by Ian Damping on 05/06/2019.
Github = https://github.com/iandamping
 */
@Database(entities = [LocalMovieData::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
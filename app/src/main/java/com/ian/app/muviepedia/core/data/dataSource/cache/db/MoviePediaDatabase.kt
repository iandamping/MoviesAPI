package com.ian.app.muviepedia.core.data.dataSource.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ian.app.muviepedia.core.data.dataSource.cache.db.dao.MovieDao
import com.ian.app.muviepedia.core.data.dataSource.cache.db.dao.TvDao
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalMovieEntity
import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.LocalTvEntity

@Database(
    entities = [LocalMovieEntity::class, LocalTvEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoviePediaDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvDao(): TvDao
}

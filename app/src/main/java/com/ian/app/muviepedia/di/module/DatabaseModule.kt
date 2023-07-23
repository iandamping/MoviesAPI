package com.ian.app.muviepedia.di.module

import android.content.Context
import androidx.room.Room
import com.ian.app.muviepedia.core.data.cache.db.MoviePediaDatabase
import com.ian.app.muviepedia.core.data.cache.db.dao.MovieDao
import com.ian.app.muviepedia.core.data.cache.db.dao.TvDao
import com.ian.app.muviepedia.di.qualifier.ApplicationContext
import com.ian.app.muviepedia.di.scope.ApplicationScoped
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    private const val DATABASE_NAME = "moviepedia.db"

    @Provides
    @ApplicationScoped
    fun provideDb(@ApplicationContext context: Context): MoviePediaDatabase {
        return Room
            .databaseBuilder(context, MoviePediaDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(db: MoviePediaDatabase): MovieDao {
        return db.movieDao()
    }

    @Provides
    fun provideTvDao(db: MoviePediaDatabase): TvDao {
        return db.tvDao()
    }
}
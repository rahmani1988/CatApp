package com.reza.database.di

import android.content.Context
import androidx.room.Room
import com.reza.database.data.db.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides the database and DAO instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the [CatDatabase] instance.
     *
     * @param appContext The application context.
     * @return The [CatDatabase] instance.
     */
    @Provides
    @Singleton
    fun provideCatDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, CatDatabase::class.java, "cat_database")
            .fallbackToDestructiveMigration()
            .build()

    /**
     * Provides the [CatDao] instance.
     *
     * @param db The [CatDatabase] instance.
     * @return The [CatDao] instance.
     */
    @Provides
    @Singleton
    fun provideCatDao(db: CatDatabase) = db.catDao()
}
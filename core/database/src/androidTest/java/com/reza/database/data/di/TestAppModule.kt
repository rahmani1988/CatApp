package com.reza.database.data.di

import android.content.Context
import androidx.room.Room
import com.reza.database.data.db.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * Hilt module that provides an in-memory database for testing.
 */
@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    /**
     * Provides an in-memory [CatDatabase] instance for testing.
     *
     * @param context The application context.
     * @return The in-memory [CatDatabase] instance.
     */
    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, CatDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}
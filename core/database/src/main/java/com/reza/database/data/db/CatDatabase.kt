package com.reza.database.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reza.database.data.model.CatEntity

/**
 * The Room database for storing cat data.
 */
@Database(
    entities = [CatEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatDatabase : RoomDatabase() {
    /**
     * Returns the DAO for interacting with the cat table.
     *
     * @return The [CatDao] instance.
     */
    abstract fun catDao(): CatDao
}
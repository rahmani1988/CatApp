package com.reza.database.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reza.database.data.model.CatEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for interacting with the cat database table.
 */
@Dao
interface CatDao {
    /**
     * Inserts a [CatEntity] into the database.
     * If a cat with the same ID already exists, it will be replaced.
     *
     * @param catEntities The [CatEntity] to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catEntities: CatEntity)

    /**
     * Retrieves all cats from the database.
     *
     * @return A list of [CatEntity] objects.
     */
    @Query("select * from cat_table")
    suspend fun getCats(): List<CatEntity>

    /**
     * Deletes a cat from the database by its ID.
     *
     * @param id The ID of the cat to delete.
     */
    @Query("DELETE FROM cat_table WHERE id = :id")
    suspend fun clear(id: String)
}
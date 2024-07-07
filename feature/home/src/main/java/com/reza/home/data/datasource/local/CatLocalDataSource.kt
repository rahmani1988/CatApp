package com.reza.home.data.datasource.local

import com.reza.database.data.model.CatEntity
import kotlinx.coroutines.flow.Flow

/**
 * Local data source interface for managing favorite cat data.
 *
 * This interface defines the operations that can be performed on favorite cat data stored locally.
 */
internal interface CatLocalDataSource {
    /**
     * Retrieves a list of favorite cats from the local data source.
     *
     * @return A list of [CatEntity] objects representing the favorite cats.
     */
    suspend fun getFavoriteCats(): List<CatEntity>

    /**
     * Inserts a favorite cat into the local data source.
     *
     * @param cat The [CatEntity] object to insert.
     */
    suspend fun insertFavoriteCat(cat: CatEntity)

    /**
     * Deletes a favorite cat from the local data source.
     *
     * @param catId The ID of the cat to delete.
     */
    suspend fun deleteFavoriteCat(catId: String)
}
package com.reza.home.data.datasource.local

import com.reza.database.data.db.CatDao
import com.reza.database.data.model.CatEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Default implementation of the [CatLocalDataSource] interface.
 *
 * This class provides access to the local database through the [CatDao] and implements the methods for managing favorite cats locally.
 *
 * @param catDao The Data Access Object for interacting with the cat database.
 */
internal class DefaultCatLocalDataSource @Inject constructor(
    private val catDao: CatDao
) : CatLocalDataSource {

    /**
     * Retrieves a list of favorite cats from the local database.
     *
     * @return A list of [CatEntity] objects representing the favorite cats.
     */
    override suspend fun getFavoriteCats(): List<CatEntity> {
        return catDao.getCats()
    }

    /**
     * Inserts a favorite cat into the local database.
     *
     * @param cat The [CatEntity] object to insert.
     */
    override suspend fun insertFavoriteCat(cat: CatEntity) {
        catDao.insert(cat)
    }

    /**
     * Deletes a favorite cat from the local database.
     *
     * @param catId The ID of the cat to delete.
     */
    override suspend fun deleteFavoriteCat(catId: String) {
        catDao.clear(catId)
    }
}
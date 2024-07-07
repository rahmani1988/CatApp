package com.reza.home.domain.repository

import com.reza.data.models.Cat

/**
 * Repository interface for managing cat data.
 *
 * This interface defines the operations that can be performed on cat data, including:
 * - Inserting a favorite cat.
 * - Removing a favorite cat.
 * - Retrieving a list of cats (with pagination support).
 */
internal interface CatRepository {
    /**
     * Inserts a cat as a favorite.
     *
     * @param cat The [Cat] object to insert as a favorite.
     */
    suspend fun insertFavoriteCat(cat: Cat)

    /**
     * Removes a cat from favorites.
     *
     * @param catId The ID of the cat to remove from favorites.
     */
    suspend fun removeFavoriteCat(catId: String)

    /**
     * Retrieves a list of cats.
     *
     * @param page The page number to retrieve (for pagination).
     * @return A list of [Cat] objects representing the retrieved cats.
     */
    suspend fun getCats(page: Int): List<Cat>
}
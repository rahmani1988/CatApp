package com.reza.home.domain.usecase

import com.reza.data.models.Cat

/**
 * A use case responsible for inserting a favorite cat.
 */
internal interface InsertFavouriteCatUseCase {
    /**
     * Inserts the given cat as a favorite.
     *
     * @param cat The [Cat] object to be marked as a favorite.
     */
    suspend fun insertFavoriteCat(cat: Cat)
}
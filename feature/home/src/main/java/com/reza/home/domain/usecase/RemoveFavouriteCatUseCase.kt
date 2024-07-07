package com.reza.home.domain.usecase

/**
 * A use case responsible for removing a favorite cat.
 */
internal interface RemoveFavouriteCatUseCase {
    /**
     * Removes the favorite cat with the given ID.
     *
     * @param catId The ID of the cat to be removed from favorites.
     */
    suspend fun removeFavoriteCat(catId: String)
}
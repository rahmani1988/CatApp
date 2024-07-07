package com.reza.home.domain.usecase

import com.reza.home.domain.repository.CatRepository
import javax.inject.Inject

/**
 * Default implementation of the [RemoveFavouriteCatUseCase] that uses a [CatRepository] to remove a favorite cat.
 */
internal class DefaultRemoveFavouriteCatUseCase @Inject constructor(private val catRepository: CatRepository) :
    RemoveFavouriteCatUseCase {

    /**
     * Removes the favorite cat with the given ID using the [CatRepository].
     *
     * @param catId The ID of the cat to be removed from favorites.
     */
    override suspend fun removeFavoriteCat(catId: String) {
        catRepository.removeFavoriteCat(catId)
    }
}
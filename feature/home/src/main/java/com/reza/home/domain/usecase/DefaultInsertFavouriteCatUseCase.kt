package com.reza.home.domain.usecase

import com.reza.data.models.Cat
import com.reza.home.domain.repository.CatRepository
import javax.inject.Inject

/**
 * Default implementation of the [InsertFavouriteCatUseCase] that uses a [CatRepository] to insert a favorite cat.
 */
internal class DefaultInsertFavouriteCatUseCase @Inject constructor(private val catRepository: CatRepository) :
    InsertFavouriteCatUseCase {

    /**
     * Inserts the given cat as a favorite using the [CatRepository].
     *
     * @param cat The [Cat] object to be marked as a favorite.
     */
    override suspend fun insertFavoriteCat(cat: Cat) {
        catRepository.insertFavoriteCat(cat)
    }
}
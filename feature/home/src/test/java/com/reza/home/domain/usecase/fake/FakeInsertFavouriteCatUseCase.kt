package com.reza.home.domain.usecase.fake

import com.reza.data.models.Cat
import com.reza.home.domain.usecase.InsertFavouriteCatUseCase

/**
 * A fake implementation of [InsertFavouriteCatUseCase] that stores favorite cats in a list.
 */
internal class FakeInsertFavouriteCatUseCase : InsertFavouriteCatUseCase {

    /**
     * A list to store favorite cats.
     */
    val cats = mutableListOf<Cat>()

    /**
     * Adds the given [Cat] to the list of favorite cats.
     *
     * @param cat The [Cat] to mark as favorite.
     */
    override suspend fun insertFavoriteCat(cat: Cat) {
        cats.add(cat)
    }
}
package com.reza.home.domain.usecase.fake

import com.reza.data.models.Cat
import com.reza.home.domain.usecase.RemoveFavouriteCatUseCase

/**
 * A fake implementation of [RemoveFavouriteCatUseCase] that removes a favorite cat from a list.
 */
internal class FakeRemoveFavouriteCatUseCase : RemoveFavouriteCatUseCase {

    /**
     * A list to store favorite cats, initially containing [Cat.DEFAULT].
     */
    val cats = mutableListOf(Cat.DEFAULT)

    /**
     * Removes the first cat from the list of favorite cats.
     *
     * @param catId Ignored in this fake implementation.
     */
    override suspend fun removeFavoriteCat(catId: String) {
        cats.removeAt(0)
    }
}
package com.reza.home.domain.usecase.fake

import com.reza.data.models.Cat
import com.reza.home.domain.usecase.GetCatsUseCase

/**
 * A fake implementation of [GetCatsUseCase] that returns a fixed list of cats.
 */
internal class FakeGetCatsUseCase : GetCatsUseCase {

    /**
     * Returns a fixed list containing a single [Cat.DEFAULT] instance.
     *
     * @param page Ignored in this fake implementation.
     * @return A list of [Cat] objects.
     */
    override suspend fun getCats(page: Int): List<Cat> {
        return listOf(Cat.DEFAULT)
    }
}
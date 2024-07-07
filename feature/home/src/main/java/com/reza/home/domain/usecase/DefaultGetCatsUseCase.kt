package com.reza.home.domain.usecase

import com.reza.data.models.Cat
import com.reza.home.domain.repository.CatRepository
import javax.inject.Inject

/**
 * Default implementation of the [GetCatsUseCase] that uses a [CatRepository] to fetch a list of cats.
 */
internal class DefaultGetCatsUseCase @Inject constructor(private val catRepository: CatRepository) :
    GetCatsUseCase {

    /**
     * Retrieves a list of cats for the specified page using the [CatRepository].
     *
     * @param page The page number to fetch.
     * @return A list of [Cat] objects for the requested page.
     */
    override suspend fun getCats(page: Int): List<Cat> {
        return catRepository.getCats(page)
    }
}
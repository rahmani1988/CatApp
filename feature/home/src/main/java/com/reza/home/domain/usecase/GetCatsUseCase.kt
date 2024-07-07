package com.reza.home.domain.usecase

import com.reza.data.models.Cat

/**
 * A use case responsible for fetching a list of cats.
 */
internal interface GetCatsUseCase {
    /**
     * Retrieves a list of cats for the specified page.
     *
     * @param page The page number to fetch.
     * @return A list of [Cat] objects for the requested page.
     */
    suspend fun getCats(page: Int): List<Cat>
}
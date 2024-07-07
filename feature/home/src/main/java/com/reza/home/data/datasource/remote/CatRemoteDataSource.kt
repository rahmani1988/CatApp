package com.reza.home.data.datasource.remote

import com.reza.network.data.model.resposne.CatDTO
import retrofit2.Response

/**
 * Remote data source interface for fetching cat data.
 *
 * This interface defines the operation for retrieving cat data from a remote source, including pagination support.
 */
internal interface CatRemoteDataSource {
    /**
     * Retrieves a list of cats from the remote data source.
     *
     * @param page The page number to retrieve (for pagination).
     * @param limit The maximum number of cats to retrieve per page.
     * @return A [Response] object containing a list of [CatDTO] objects representing the retrieved cats.
     */
    suspend fun getCats(page: Int, limit: Int): Response<List<CatDTO>>
}
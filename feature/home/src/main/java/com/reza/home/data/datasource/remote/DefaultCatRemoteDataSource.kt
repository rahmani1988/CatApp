package com.reza.home.data.datasource.remote

import com.reza.network.data.api.ApiService
import com.reza.network.data.model.resposne.CatDTO
import retrofit2.Response
import javax.inject.Inject

/**
 * Default implementation of the [CatRemoteDataSource] interface.
 *
 * This class uses the [ApiService] to fetch cat data from a remote source and provides a method for retrieving cats with pagination support.
 *
 * @param apiService The service for making API requests to fetch cat data.
 */
internal class DefaultCatRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    CatRemoteDataSource {

    /**
     * Retrieves a list of cats from the remote data source using the [ApiService].
     *
     * @param page The page number to retrieve (for pagination).
     * @param limit The maximum number of cats to retrieve per page.
     * @return A [Response] object containing a list of [CatDTO] objects representing the retrieved cats.
     */
    override suspend fun getCats(page: Int, limit: Int): Response<List<CatDTO>> {
        return apiService.getCats(page = page, limit = limit)
    }
}
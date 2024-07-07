package com.reza.network.data.api

import com.reza.network.data.model.resposne.CatDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service for making API requests to fetch cat data.
 */
interface ApiService {

    /**
     * Retrieves a list of cats from the API.
     *
     * @param limit The maximum number of cats to retrieve.
     * @param page The page number to retrieve.
     * @return A [Response] object containing a list of [CatDTO] objects representing the retrieved cats.
     */
    @GET("breeds")
    suspend fun getCats(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<List<CatDTO>>
}
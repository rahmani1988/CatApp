package com.reza.home.data.repository

import com.reza.common.jsonparser.JsonParser
import com.reza.common.stringresolver.StringResolver
import com.reza.data.models.Cat
import com.reza.database.data.model.CatEntity
import com.reza.home.R
import com.reza.home.data.datasource.local.CatLocalDataSource
import com.reza.home.data.datasource.remote.CatRemoteDataSource
import com.reza.home.domain.mapper.toCatEntity
import com.reza.home.domain.mapper.toCatList
import com.reza.home.domain.repository.CatRepository
import javax.inject.Inject

/**
 * Default implementation of the [CatRepository] interface.
 *
 * This class combines data from local and remote data sources to provide a unified view of cat data.
 *
 * @param localDataSource The local data source for accessing cached cat data.
 * @param remoteDataSource The remote data source for fetching cat data from the API.
 * @param stringResolver The [StringResolver] used to resolve string resources.
 * @param jsonParser The [JsonParser] used to parse JSON responses.
 */
internal class DefaultCatRepository @Inject constructor(
    private val localDataSource: CatLocalDataSource,
    private val remoteDataSource: CatRemoteDataSource,
    private val stringResolver: StringResolver,
    private val jsonParser: JsonParser
) : CatRepository {

    companion object {
        const val PAGE_LIMIT = 20
    }

    /**
     * Inserts a cat as a favorite in the local data source.
     *
     * @param cat The [Cat] object to insert as a favorite.
     */
    override suspend fun insertFavoriteCat(cat: Cat) {
        localDataSource.insertFavoriteCat(cat.toCatEntity())
    }

    /**
     * Removes a cat from favorites in the local data source.
     *
     * @param catId The ID of the cat to remove from favorites.
     */
    override suspend fun removeFavoriteCat(catId: String) {
        localDataSource.deleteFavoriteCat(catId)
    }

    /**
     * Retrieves a list of cats, combining data from the remote and local data sources.
     *
     * Fetches cats from the remote data source and merges them with favorite cats from the local data source.
     * Favorite cats are marked with `isFavourite = true`.
     *
     * @param page The page number to retrieve.
     * @return A list of [Cat] objects.
     * @throws Exception if an error occurs while fetching cats from the remote data source.
     */
    override suspend fun getCats(page: Int): List<Cat> =
        localDataSource.getFavoriteCats()
            .let { favoriteCats ->
                val cats =
                    remoteDataSource.getCats(page = page, limit = PAGE_LIMIT).run {
                        if (isSuccessful) {
                            body()?.toCatList() ?: emptyList()
                        } else {
                            errorBody()?.string()?.let { errorBody ->
                                throw Exception(jsonParser.parseJson(errorBody))
                            }
                                ?: throw Exception(stringResolver.getString(R.string.something_went_wrong_please_try_again_later))
                        }
                    }
                if (favoriteCats.isEmpty()) {
                    cats
                } else {
                    cats.map { cat ->
                        if (favoriteCats.contains(CatEntity(id = cat.id))) {
                            cat.copy(isFavourite = true)
                        } else {
                            cat
                        }
                    }
                }
            }
}
package com.reza.home.data.repository

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.reza.common.jsonparser.JsonParser
import com.reza.common.stringresolver.StringResolver
import com.reza.data.models.Cat
import com.reza.database.data.model.CatEntity
import com.reza.home.data.datasource.local.CatLocalDataSource
import com.reza.home.data.datasource.remote.CatRemoteDataSource
import com.reza.home.domain.mapper.toCatEntity
import com.reza.home.domain.repository.CatRepository
import com.reza.network.data.model.resposne.CatDTO
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
internal class DefaultCatRepositoryTest {

    @Mock
    private lateinit var localDataSource: CatLocalDataSource

    @Mock
    private lateinit var remoteDataSource: CatRemoteDataSource

    @Mock
    private lateinit var stringResolver: StringResolver

    @Mock
    private lateinit var jsonParser: JsonParser

    private lateinit var catRepository: CatRepository

    private val responseRemoteSuccessful =
        Response.success<List<CatDTO>>(200, listOf(CatDTO.DEFAULT, CatDTO.SECONDARY))

    private val responseRemoteError = Response.error<List<CatDTO>>(
        400,
        "{\"message\":[\"sample error\"]}"
            .toResponseBody("application/json".toMediaTypeOrNull())
    )

    @Before
    fun setUp() {
        catRepository = DefaultCatRepository(
            localDataSource = localDataSource, remoteDataSource = remoteDataSource,
            stringResolver = stringResolver, jsonParser = jsonParser
        )
    }

    @After
    fun tearDown() {
        /* NO-OP */
    }

    @Test
    fun `should call insertFavoriteCat`() = runTest {
        catRepository.insertFavoriteCat(cat = Cat.DEFAULT)

        Mockito.verify(localDataSource, times(1)).insertFavoriteCat(cat = Cat.DEFAULT.toCatEntity())
    }

    @Test
    fun `should call deleteFavoriteCat`() = runTest {
        catRepository.removeFavoriteCat(catId = Cat.DEFAULT.id)

        Mockito.verify(localDataSource, times(1)).deleteFavoriteCat(catId = anyString())
    }

    @Test
    fun `should not modify the cat list coming from remote when there is no favorite cat in the db`() =
        runTest {
            Mockito.`when`(localDataSource.getFavoriteCats()).thenReturn(emptyList())
            Mockito.`when`(remoteDataSource.getCats(page = anyInt(), limit = anyInt()))
                .thenReturn(responseRemoteSuccessful)

            val result = catRepository.getCats(page = 0)

            assertThat(result[0].isFavourite).isFalse()
            assertThat(result[1].isFavourite).isFalse()
        }

    @Test
    fun `should modify the first item of cat list coming from remote when there is a favorite cat in the db`() =
        runTest {
            Mockito.`when`(localDataSource.getFavoriteCats()).thenReturn(listOf(CatEntity.DEFAULT))
            Mockito.`when`(remoteDataSource.getCats(page = anyInt(), limit = anyInt()))
                .thenReturn(responseRemoteSuccessful)

            val result = catRepository.getCats(page = 0)

            assertThat(result[0].isFavourite).isTrue()
            assertThat(result[1].isFavourite).isFalse()
        }

    @Test
    fun `should throw exception when remote result is not successful`() = runTest {
        Mockito.`when`(localDataSource.getFavoriteCats())
            .thenReturn(listOf(CatEntity.DEFAULT))
        Mockito.`when`(
            remoteDataSource.getCats(
                page = anyInt(),
                limit = anyInt()
            )
        ).thenReturn(responseRemoteError)
        Mockito.`when`(jsonParser.parseJson(anyString())).thenReturn(
            "error"
        )

        try {
            catRepository.getCats(page = 0)
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(
                Exception::class.java
            )
        }
    }
}
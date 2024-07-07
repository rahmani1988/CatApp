package com.reza.home.data.datasource.remote

import com.reza.network.data.api.ApiService
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DefaultCatRemoteDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService
    private lateinit var catRemoteDataSource: CatRemoteDataSource

    @Before
    fun setUp() {
        catRemoteDataSource = DefaultCatRemoteDataSource(apiService = apiService)
    }

    @After
    fun tearDown() {
        /* NO-OP */
    }

    @Test
    fun `should call getCats`() = runTest {
        catRemoteDataSource.getCats(page = 0, limit = 10)
        Mockito.verify(apiService, times(1)).getCats(page = anyInt(), limit = anyInt())
    }
}
package com.reza.home.domain.usecase

import com.reza.home.domain.repository.CatRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DefaultRemoveFavouriteCatUseCaseTest {

    @Mock
    private lateinit var catRepository: CatRepository
    private lateinit var removeFavouriteCatUseCase: RemoveFavouriteCatUseCase

    @Before
    fun setUp() {
        removeFavouriteCatUseCase = DefaultRemoveFavouriteCatUseCase(catRepository = catRepository)
    }

    @After
    fun tearDown() {
        /* NO-OP */
    }

    @Test
    fun `should call removeFavoriteCat`() = runTest {
        removeFavouriteCatUseCase.removeFavoriteCat(catId = "cat1")
        Mockito.verify(catRepository, times(1)).removeFavoriteCat(anyString())
    }
}
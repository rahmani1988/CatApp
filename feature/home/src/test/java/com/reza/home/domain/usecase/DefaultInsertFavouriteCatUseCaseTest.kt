package com.reza.home.domain.usecase

import com.reza.data.models.Cat
import com.reza.home.domain.repository.CatRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DefaultInsertFavouriteCatUseCaseTest {

    @Mock
    private lateinit var catRepository: CatRepository
    private lateinit var insertFavouriteCatUseCase: InsertFavouriteCatUseCase

    @Before
    fun setUp() {
        insertFavouriteCatUseCase =
            DefaultInsertFavouriteCatUseCase(catRepository = catRepository)
    }

    @After
    fun tearDown() {
        /* NO-OP */
    }

    @Test
    fun `should call insertFavoriteCat`() = runTest {
        insertFavouriteCatUseCase.insertFavoriteCat(Cat.DEFAULT)
        Mockito.verify(catRepository, times(1)).insertFavoriteCat(cat = Cat.DEFAULT)
    }
}
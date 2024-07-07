package com.reza.home.domain.usecase


import com.reza.home.domain.repository.CatRepository
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
internal class DefaultGetCatsUseCaseTest {

    @Mock
    private lateinit var catRepository: CatRepository
    private lateinit var getCatsUseCase: GetCatsUseCase

    @Before
    fun setUp() {
        getCatsUseCase = DefaultGetCatsUseCase(catRepository = catRepository)
    }

    @After
    fun tearDown() {
        /* NO-OP */
    }

    @Test
    fun `should call getCats`() = runTest {
        getCatsUseCase.getCats(page = 0)
        Mockito.verify(catRepository, times(1)).getCats(anyInt())
    }
}
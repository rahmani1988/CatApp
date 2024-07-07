package com.reza.home.presentation

import com.google.common.truth.Truth.assertThat
import com.reza.data.models.Cat
import com.reza.home.domain.usecase.GetCatsUseCase
import com.reza.home.domain.usecase.InsertFavouriteCatUseCase
import com.reza.home.domain.usecase.RemoveFavouriteCatUseCase
import com.reza.home.domain.usecase.fake.FakeGetCatsUseCase
import com.reza.home.domain.usecase.fake.FakeInsertFavouriteCatUseCase
import com.reza.home.domain.usecase.fake.FakeRemoveFavouriteCatUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class HomeViewModelTest {

    private lateinit var getCatsUseCase: GetCatsUseCase
    private lateinit var insertFavouriteCatUseCase: InsertFavouriteCatUseCase
    private lateinit var removeFavouriteCatUseCase: RemoveFavouriteCatUseCase

    private lateinit var homeViewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        getCatsUseCase = FakeGetCatsUseCase()
        insertFavouriteCatUseCase = FakeInsertFavouriteCatUseCase()
        removeFavouriteCatUseCase = FakeRemoveFavouriteCatUseCase()

        homeViewModel = HomeViewModel(
            getCatsUseCase = getCatsUseCase,
            insertFavouriteCatUseCase = insertFavouriteCatUseCase,
            removeFavouriteCatUseCase = removeFavouriteCatUseCase,
            mainDispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        /* NO-OP */
    }

    @Test
    fun `should call remove favourite cat when OnFavouriteCatClick is propagated and isFavourite is true`() =
        testScope.runTest {
            homeViewModel.onEvent(HomeUiEvent.OnFavouriteCatClick(Cat.DEFAULT.copy(isFavourite = true)))

            advanceUntilIdle()
            assertThat((removeFavouriteCatUseCase as FakeRemoveFavouriteCatUseCase).cats.size).isEqualTo(
                0
            )
        }

    @Test
    fun `should call insert favourite cat when OnFavouriteCatClick is propagated and isFavourite is false`() =
        testScope.runTest {
            homeViewModel.onEvent(HomeUiEvent.OnFavouriteCatClick(Cat.DEFAULT.copy(isFavourite = false)))

            advanceUntilIdle()
            assertThat((insertFavouriteCatUseCase as FakeInsertFavouriteCatUseCase).cats.size).isEqualTo(
                1
            )
        }

    @Test
    fun `should add item to catList when GetCats is propagated`() = testScope.runTest {
        // getCats has been called in init block

        assertThat(homeViewModel.catList.toList().size).isEqualTo(1)
    }
    @Test
    fun `should update _listState with IDLE when GetCats is propagated`() = testScope.runTest {
        // getCats has been called in init block

        assertThat(homeViewModel.listState).isEqualTo(ListState.IDLE)
    }

    @Test
    fun `should update catList when GetCats is propagated for second time`() = testScope.runTest {
        homeViewModel.setPage(1)
        homeViewModel.canPaginate(true)

        homeViewModel.onEvent(HomeUiEvent.GetCats)
        advanceUntilIdle()

        assertThat(homeViewModel.catList.toList().size).isEqualTo(2)
    }
}
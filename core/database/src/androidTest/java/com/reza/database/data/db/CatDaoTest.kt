package com.reza.database.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.reza.database.data.model.CatEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class CatDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var catDatabase: CatDatabase

    private lateinit var catDao: CatDao

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        hiltRule.inject()
        catDao = catDatabase.catDao()
    }

    @After
    fun tearDown() {
        catDatabase.close()
    }

    @Test
    fun insertCat_returnsCorrectList() = testScope.runTest {
        val catEntity1 = CatEntity.DEFAULT
        val catEntity2 = CatEntity.SECONDARY
        catDao.insert(catEntities = catEntity1)
        catDao.insert(catEntities = catEntity2)

        val result = catDao.getCats()
        assertThat(result).isEqualTo(listOf(catEntity1, catEntity2))
    }

    @Test
    fun clearCat_returnsCorrectList() = testScope.runTest {
        val catEntity1 = CatEntity.DEFAULT
        val catEntity2 = CatEntity.SECONDARY
        catDao.insert(catEntities = catEntity1)
        catDao.insert(catEntities = catEntity2)
        catDao.clear(id = CatEntity.DEFAULT.id)

        val result = catDao.getCats()
        assertThat(result).isEqualTo(listOf(catEntity2))
    }
}
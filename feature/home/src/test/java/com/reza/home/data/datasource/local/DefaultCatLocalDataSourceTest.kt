package com.reza.home.data.datasource.local

import com.reza.database.data.db.CatDao
import com.reza.database.data.model.CatEntity
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
internal class DefaultCatLocalDataSourceTest {

    @Mock
    private lateinit var catDao: CatDao
    private lateinit var catLocalDataSource: CatLocalDataSource

    @Before
    fun setUp() {
        catLocalDataSource = DefaultCatLocalDataSource(catDao = catDao)
    }

    @After
    fun tearDown() {
        /* NO-OP */
    }

    @Test
    fun `should call getCatsFor with page`() = runTest {
        catLocalDataSource.getFavoriteCats()
        Mockito.verify(catDao, times(1)).getCats()
    }

    @Test
    fun `should call insert with catEntity`() = runTest {
        catLocalDataSource.insertFavoriteCat(cat = CatEntity.DEFAULT)
        Mockito.verify(catDao, times(1)).insert(catEntities = CatEntity.DEFAULT)
    }

    @Test
    fun `should call clearCat with catId`() = runTest {
        val catId = "cat1"
        catLocalDataSource.deleteFavoriteCat(catId = catId)
        Mockito.verify(catDao, times(1)).clear(id = catId)
    }
}
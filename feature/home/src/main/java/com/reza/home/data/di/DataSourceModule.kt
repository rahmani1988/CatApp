package com.reza.home.data.di

import com.reza.home.data.datasource.local.CatLocalDataSource
import com.reza.home.data.datasource.local.DefaultCatLocalDataSource
import com.reza.home.data.datasource.remote.CatRemoteDataSource
import com.reza.home.data.datasource.remote.DefaultCatRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Hilt module for providing data source dependencies.
 *
 * This module defines the bindings for the [CatLocalDataSource] and [CatRemoteDataSource] interfaces,
 * allowing Hilt to inject the default implementations into the repository.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataSourceModule {

    /**
     * Binds the [DefaultCatLocalDataSource] implementation to the [CatLocalDataSource] interface.
     */
    @Binds
    internal abstract fun bindCatLocalDataSource(
        defaultCatLocalDataSource: DefaultCatLocalDataSource
    ): CatLocalDataSource

    /**
     * Binds the [DefaultCatRemoteDataSource] implementation to the [CatRemoteDataSource] interface.
     */
    @Binds
    internal abstract fun bindCatRemoteDataSource(
        defaultCatRemoteDataSource: DefaultCatRemoteDataSource
    ): CatRemoteDataSource
}
package com.reza.home.data.di

import com.reza.home.data.repository.DefaultCatRepository
import com.reza.home.domain.repository.CatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Hilt module for providing the cat repository dependency.
 *
 * This module defines the binding for the [CatRepository] interface,
 * allowing Hilt to inject the default implementation into view models or other components.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    /**
     * Binds the [DefaultCatRepository] implementation to the [CatRepository] interface.
     */
    @Binds
    internal abstract fun bindCatRepository(
        defaultCatRepository: DefaultCatRepository
    ): CatRepository
}
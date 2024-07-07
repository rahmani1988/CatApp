package com.reza.home.data.di

import com.reza.home.domain.usecase.DefaultGetCatsUseCase
import com.reza.home.domain.usecase.DefaultInsertFavouriteCatUseCase
import com.reza.home.domain.usecase.DefaultRemoveFavouriteCatUseCase
import com.reza.home.domain.usecase.GetCatsUseCase
import com.reza.home.domain.usecase.InsertFavouriteCatUseCase
import com.reza.home.domain.usecase.RemoveFavouriteCatUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Dagger module for providing use case dependencies.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {

    /**
     * Binds the [DefaultGetCatsUseCase] implementation to the [GetCatsUseCase] interface.
     */
    @Binds
    internal abstract fun bindGetCatsUseCase(
        defaultGetCatsUseCase: DefaultGetCatsUseCase
    ): GetCatsUseCase

    /**
     * Binds the [DefaultInsertFavouriteCatUseCase] implementation to the [InsertFavouriteCatUseCase] interface.
     */
    @Binds
    internal abstract fun bindInsertFavouriteCatUseCase(
        defaultInsertFavouriteCatUseCase: DefaultInsertFavouriteCatUseCase
    ): InsertFavouriteCatUseCase

    /**
     * Binds the [DefaultRemoveFavouriteCatUseCase] implementation to the [RemoveFavouriteCatUseCase] interface.
     */
    @Binds
    internal abstract fun bindRemoveFavouriteCatUseCaseUseCase(
        defaultGetCatsUseCase: DefaultRemoveFavouriteCatUseCase
    ): RemoveFavouriteCatUseCase
}
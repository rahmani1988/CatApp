package com.reza.di

import com.reza.common.jsonparser.DefaultJsonParser
import com.reza.common.jsonparser.JsonParser
import com.reza.common.stringresolver.DefaultStringResolver
import com.reza.common.stringresolver.StringResolver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Hilt module for providing common dependencies.
 *
 * This module defines the binding for the [StringResolver] interface,
 * allowing Hilt to inject the default implementation into view models or other components.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class CommonModule {

    /**
     * Binds the [DefaultStringResolver] implementation to the [StringResolver] interface.
     */
    @Binds
    abstract fun bindStringResolver(
        defaultStringResolver: DefaultStringResolver
    ): StringResolver

    /**
     * Binds the [DefaultJsonParser] implementation to the [JsonParser] interface.
     */
    @Binds
    abstract fun bindJsonParser(
        defaultJsonParser: DefaultJsonParser
    ): JsonParser
}
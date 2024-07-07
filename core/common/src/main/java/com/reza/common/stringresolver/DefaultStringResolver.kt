package com.reza.common.stringresolver

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default implementation of the [StringResolver] interface.
 *
 * This class provides a way to resolve string resources from the application context.
 *
 * @param context The application context.
 */
@Singleton
class DefaultStringResolver @Inject constructor(@ApplicationContext private val context: Context) : StringResolver {

    /**
     * Retrieves a string resource by its ID.
     *
     * @param id The ID of the string resource.
     * @return The string resource associated with the given ID.
     */
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}
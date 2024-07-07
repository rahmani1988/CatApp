package com.reza.common.stringresolver

import androidx.annotation.StringRes

/**
 * Interface for resolving string resources.
 */
interface StringResolver {
    /**
     * Retrieves a string resource by its ID.
     *
     * @param id The ID of the string resource.
     * @return The string resource associated with the given ID.
     */
    fun getString(@StringRes id: Int): String
}
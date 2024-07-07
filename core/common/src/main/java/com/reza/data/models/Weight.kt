package com.reza.data.models

import kotlinx.serialization.Serializable

/**
 * Data class representing the weight of a cat.
 *
 * @param metric The weight in metric units.
 */
@Serializable
data class Weight(
    val metric: String
)

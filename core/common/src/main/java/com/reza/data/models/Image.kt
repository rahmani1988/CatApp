package com.reza.data.models

import kotlinx.serialization.Serializable

/**
 * Data class representing an image.
 *
 * @param url The URL of the image.
 */
@Serializable
data class Image(
    val url: String
)

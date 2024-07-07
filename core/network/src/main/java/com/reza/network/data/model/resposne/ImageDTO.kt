package com.reza.network.data.model.resposne

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object representing an image fetched from the API.
 */
data class ImageDTO(
    @SerializedName("id") val id: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("url") val url: String?
)

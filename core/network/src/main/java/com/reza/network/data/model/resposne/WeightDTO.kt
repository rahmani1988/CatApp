package com.reza.network.data.model.resposne

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object representing the weight of a cat fetched from the API.
 */
data class WeightDTO(
    @SerializedName("imperial") val imperial: String?,
    @SerializedName("metric") val metric: String?
)
package com.reza.detail.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Detail(
    val image: String,
    val name: String,
    val description: String
)
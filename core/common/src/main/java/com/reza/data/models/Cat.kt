package com.reza.data.models

import kotlinx.serialization.Serializable

/**
 * Data class representing a cat.
 *
 * @param weight The weight of the cat.
 * @param id The unique identifier of the cat.
 * @param name The name of the cat.
 * @param wikipediaUrl The URL to the Wikipedia page for the cat breed.
 * @param temperament The temperament of the cat breed.
 * @param origin The origin of the cat breed.
 * @param description A description of the cat breed.
 * @param lifeSpan The typical lifespan of the cat breed.
 * @param image Image information for the cat breed.
 * @param isFavourite Indicates whether the cat is marked as a favorite.
 */
@Serializable
data class Cat(
    val weight: Weight,
    val id: String,
    val name: String,
    val wikipediaUrl: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val lifeSpan: String,
    val image: Image,
    val isFavourite: Boolean = false
) {
    companion object {
        /**
         * A default instance of [Cat] with empty values.
         */
        val DEFAULT = Cat(
            weight = Weight(metric = ""),
            name = "", id = "",
            wikipediaUrl = "", temperament = "",
            origin = "", description = "",
            lifeSpan = "", image = Image(url = ""),
            isFavourite = false
        )
    }
}
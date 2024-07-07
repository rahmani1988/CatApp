package com.reza.home.domain.mapper

import com.reza.data.models.Cat
import com.reza.data.models.Image
import com.reza.data.models.Weight
import com.reza.database.data.model.CatEntity
import com.reza.network.data.model.resposne.CatDTO
import com.reza.network.data.model.resposne.ImageDTO
import com.reza.network.data.model.resposne.WeightDTO

/**
 * Converts a [WeightDTO] object to a [Weight] object.
 *
 * This extension function takes a [WeightDTO] object and maps its `metric` property to the `metric` property of a new [Weight] object.
 * If the `metric` property in the [WeightDTO] is null, an empty string is used instead.
 *
 * @return A [Weight] object representing the converted weight.
 */
internal fun WeightDTO.toWeight() = Weight(metric = this.metric ?: "")

/**
 * Converts an [ImageDTO] object to an [Image] object.
 *
 * This extension function takes an [ImageDTO] object and maps its `url` property to the `url` property of a new [Image] object.
 * If the `url` property in the [ImageDTO] is null, an empty string is used instead.
 *
 * @return An [Image] object representing the converted image.
 */
internal fun ImageDTO.toImage() = Image(url = this.url ?: "")

/**
 * Converts a [CatDTO] object to a [Cat] object.
 *
 * This extension function takes a [CatDTO] object and maps its properties to the corresponding properties of a new [Cat] object.
 * It handles null values by providing default values (empty strings or default objects) for optional properties.
 *
 * It utilizes the previously defined extension functions `toWeight()` and `toImage()` to convert nested DTO objects.
 *
 * @return A [Cat] object representing the converted cat data.
 */
internal fun CatDTO.toCat() = Cat(
    weight = this.weight?.toWeight() ?: Weight(""),
    id = this.id ?: "",
    name = this.name ?: "",
    wikipediaUrl = this.wikipediaUrl ?: "",
    temperament = this.temperament ?: "",
    origin = this.origin ?: "",
    description = this.description ?: "",
    lifeSpan = this.lifeSpan ?: "",
    image = this.image?.toImage() ?: Image(""),
)

/**
 * Converts a list of [CatDTO] objects to a list of [Cat] objects.
 *
 * This extension function takes a list of [CatDTO] objects and maps each element to a [Cat] object using the `toCat()` extension function.
 *
 * @return A list of [Cat] objects representing the converted cat data.
 */
internal fun List<CatDTO>.toCatList() = this.map { it.toCat() }

/**
 * Converts a [Cat] object to a [CatEntity] object.
 *
 * This extension function takes a [Cat] object and maps its `id` property to the `id` property of a new [CatEntity] object.
 *
 * @return A [CatEntity] object representing the converted cat data for database storage.
 */
internal fun Cat.toCatEntity() = CatEntity(id = this.id)

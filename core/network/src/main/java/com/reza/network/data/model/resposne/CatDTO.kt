package com.reza.network.data.model.resposne

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object representing a cat fetched from the API.
 */
data class CatDTO(
    @SerializedName("weight") var weight: WeightDTO?,
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("cfa_url") var cfaUrl: String?,
    @SerializedName("vetstreet_url") var vetstreetUrl: String?,
    @SerializedName("vcahospitals_url") var vcahospitalsUrl: String?,
    @SerializedName("temperament") var temperament: String?,
    @SerializedName("origin") var origin: String?,
    @SerializedName("country_codes") var countryCodes: String?,
    @SerializedName("country_code") var countryCode: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("life_span") var lifeSpan: String?,
    @SerializedName("indoor") var indoor: Int?,
    @SerializedName("lap") var lap: Int?,
    @SerializedName("alt_names") var altNames: String?,
    @SerializedName("adaptability") var adaptability: Int?,
    @SerializedName("affection_level") var affectionLevel: Int?,
    @SerializedName("child_friendly") var childFriendly: Int?,
    @SerializedName("dog_friendly") var dogFriendly: Int?,
    @SerializedName("energy_level") var energyLevel: Int?,
    @SerializedName("grooming") var grooming: Int?,
    @SerializedName("health_issues") var healthIssues: Int?,
    @SerializedName("intelligence") var intelligence: Int?,
    @SerializedName("shedding_level") var sheddingLevel: Int?,
    @SerializedName("social_needs") var socialNeeds: Int?,
    @SerializedName("stranger_friendly") var strangerFriendly: Int?,
    @SerializedName("vocalisation") var vocalisation: Int?,
    @SerializedName("experimental") var experimental: Int?,
    @SerializedName("hairless") var hairless: Int?,
    @SerializedName("natural") var natural: Int?,
    @SerializedName("rare") var rare: Int?,
    @SerializedName("rex") var rex: Int?,
    @SerializedName("suppressed_tail") var suppressedTail: Int?,
    @SerializedName("short_legs") var shortLegs: Int?,
    @SerializedName("wikipedia_url") var wikipediaUrl: String?,
    @SerializedName("hypoallergenic") var hypoallergenic: Int?,
    @SerializedName("reference_image_id") var referenceImageId: String?,
    @SerializedName("image") var image: ImageDTO?
) {
    companion object {
        val DEFAULT = CatDTO(
            weight = null,
            id = "default", name = null,
            cfaUrl = null, vetstreetUrl = null,
            vcahospitalsUrl = null, temperament = null,
            origin = null, countryCodes = null,
            description = null, lifeSpan = null,
            countryCode = null, sheddingLevel = null,
            socialNeeds = null, healthIssues = null,
            grooming = null, energyLevel = null,
            dogFriendly = null, childFriendly = null,
            affectionLevel = null, adaptability = null,
            altNames = null, indoor = null,
            hypoallergenic = null, wikipediaUrl = null,
            shortLegs = null, suppressedTail = null,
            rex = null, rare = null, natural = null,
            hairless = null, experimental = null,
            vocalisation = null, strangerFriendly = null,
            intelligence = null, lap = null,
            image = null, referenceImageId = null
        )
        val SECONDARY = CatDTO(
            weight = null, id = "secondary",
            name = null, cfaUrl = null,
            vetstreetUrl = null, vcahospitalsUrl = null,
            temperament = null, origin = null,
            countryCodes = null, description = null,
            lifeSpan = null, countryCode = null,
            sheddingLevel = null, socialNeeds = null,
            healthIssues = null, grooming = null,
            energyLevel = null, dogFriendly = null,
            childFriendly = null, affectionLevel = null,
            adaptability = null, altNames = null,
            indoor = null, hypoallergenic = null,
            wikipediaUrl = null, shortLegs = null,
            suppressedTail = null, rex = null,
            rare = null, natural = null, hairless = null,
            experimental = null, vocalisation = null,
            strangerFriendly = null, intelligence = null,
            lap = null, image = null,
            referenceImageId = null
        )
    }
}

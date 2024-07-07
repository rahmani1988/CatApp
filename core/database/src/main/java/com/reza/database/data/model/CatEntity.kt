package com.reza.database.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a cat entity in the database.
 *
 * @property id The unique identifier of the cat. This is the primary key of the 'cat_table'.
 */
@Entity(tableName = "cat_table")
data class CatEntity(
    @PrimaryKey val id: String
) {
    companion object {
        /**
         * A default instance of [CatEntity] with the ID "DEFAULT".
         */
        val DEFAULT = CatEntity("default")
        val SECONDARY = CatEntity("secondary")
    }
}

package com.reza.common.jsonparser

/**
 * Interface for parsing JSON strings.
 */
interface JsonParser {
    /**
     * Parses a JSON string and extracts relevant information.
     *
     * @param jsonString The JSON string to parse.
     * @return The parsed information as a string.
     */
    fun parseJson(jsonString: String): String
}
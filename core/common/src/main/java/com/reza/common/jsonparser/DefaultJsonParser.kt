package com.reza.common.jsonparser

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.reza.common.R
import com.reza.common.stringresolver.DefaultStringResolver
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_ERROR_MESSAGE = "message"

/**
 * Default implementation of the [JsonParser] interface.
 *
 * This class uses Gson to parse JSON strings and extract error messages.
 *
 * @param gson The Gson instance used for JSON parsing.
 * @param stringResolver The [StringResolver] used to resolve string resources.
 */
@Singleton
class DefaultJsonParser @Inject constructor(
    private val gson: Gson,
    private val stringResolver: DefaultStringResolver
) : JsonParser {

    /**
     * Parses a JSON string and extracts the error message.
     *
     * If the JSON string contains a key named "message", its value is returned as the error message.
     * Otherwise, a generic error message is returned from the string resources.
     *
     * @param jsonString The JSON string to parse.
     * @return The extracted error message.
     */
    override fun parseJson(jsonString: String): String {
        val mapAdapter = gson.getAdapter(object : TypeToken<Map<String, Any?>>() {})
        val model: Map<String, Any?> = mapAdapter.fromJson(jsonString)
        return model.getOrDefault(
            KEY_ERROR_MESSAGE,
            stringResolver.getString(R.string.something_went_wrong_please_try_again_later)
        ).toString()
    }
}
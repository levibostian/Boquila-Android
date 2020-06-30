package com.levibostian.moshiboquilaplugin

import com.levibostian.boquila.plugins.RemoteConfigAdapterPlugin
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MoshiRemoteConfigAdapterPlugin(val moshi: Moshi): RemoteConfigAdapterPlugin() {

    /**
     * Handles Lists and non-lists with the same function. Moshi handles json arrays differently the non-arrays: https://github.com/square/moshi#parse-json-arrays
     */
    override fun <T: Any> transformStringValue(value: String, clazz: Class<T>): T? {
        val value = value.trim()
        if (value.isBlank()) return null

        if (value[0] == '[') throw IllegalArgumentException("Value from remote config service is a JSON array. You should be using `transformStringValues()` instead.")

        val jsonAdapter = moshi.adapter(clazz)

        return jsonAdapter.fromJson(value)
    }

    override fun <T : Any> transformStringValues(value: String, clazz: Class<T>): List<T>? {
        val value = value.trim()
        if (value.isBlank()) return null

        if (value[0] != '[') throw IllegalArgumentException("Value from remote config service is *not* a JSON array. You should be using `transformStringValue()` instead.")

        val type = Types.newParameterizedType(List::class.java, clazz)
        val jsonAdapter = moshi.adapter<List<T>>(type)

        return jsonAdapter.fromJson(value)
    }

    override fun <T: Any> transformToStringValue(value: T): String? {
        val jsonAdapter = moshi.adapter<T>(value::class.java)

        return jsonAdapter.toJson(value)
    }

}
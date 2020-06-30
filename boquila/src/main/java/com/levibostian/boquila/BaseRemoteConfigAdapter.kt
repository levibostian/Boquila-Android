package com.levibostian.boquila

import com.levibostian.boquila.plugins.RemoteConfigAdapterPlugin

abstract class BaseRemoteConfigAdapter(protected val plugins: List<RemoteConfigAdapterPlugin>): RemoteConfigAdapter {

    abstract fun getStringValue(id: String): String?

    override fun <T: Any> getValue(id: String, clazz: Class<T>): T? {
        var stringValue = this.getStringValue(id) ?: return null
        if (stringValue.isBlank()) return null // firebase returns empty values when a remote config value does not exist.

        // Allow plugins to transform the input string.
        this.plugins.forEach { plugin  ->
            stringValue = plugin.manipulateStringValue(stringValue)
        }

        // Transform the string value into something else. Allow the plugins to do this for us.
        var transformedValue: T? = null
        this.plugins.forEach { plugin  ->
            if (transformedValue == null) {
                transformedValue = plugin.transformStringValue(stringValue, clazz)
            }
        }

        return transformedValue
    }

    override fun <T : Any> getValues(id: String, clazz: Class<T>): List<T>? {
        var stringValue = this.getStringValue(id) ?: return null
        if (stringValue.isBlank()) return null // firebase returns empty values when a remote config value does not exist.

        // Allow plugins to transform the input string.
        this.plugins.forEach { plugin  ->
            stringValue = plugin.manipulateStringValue(stringValue)
        }

        // Transform the string value into something else. Allow the plugins to do this for us.
        var transformedValue: List<T>? = null
        this.plugins.forEach { plugin  ->
            if (transformedValue == null) {
                transformedValue = plugin.transformStringValues(stringValue, clazz)
            }
        }

        return transformedValue
    }

    override fun <T : Any> getValues(id: String, clazz: Class<T>, defaultValue: List<T>): List<T> = this.getValues(id, clazz) ?: defaultValue

    override fun <T: Any> getValue(id: String, clazz: Class<T>, defaultValue: T): T = this.getValue(id, clazz) ?: defaultValue

}
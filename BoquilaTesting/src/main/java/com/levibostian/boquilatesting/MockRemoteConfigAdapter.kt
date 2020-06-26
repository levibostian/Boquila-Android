package com.levibostian.boquilatesting

import com.levibostian.boquila.BaseRemoteConfigAdapter
import com.levibostian.boquila.RemoteConfigAdapter
import com.levibostian.boquila.plugins.RemoteConfigAdapterPlugin

open class MockRemoteConfigAdapter(plugins: List<RemoteConfigAdapterPlugin>): BaseRemoteConfigAdapter(plugins) {

    /**
    Stores the values you want to override in the mock.
     */
    open var valueOverrides: MutableMap<String, String> = mutableMapOf()

    /**
    Attempts to transform the value you pass into something else usable
     */
    open fun setValue(id: String, value: String) {
        this.valueOverrides[id] = value
    }

    open fun <T> setValue(id: String, value: T) {
        var transformedValue: String? = null
        this.plugins.forEach { plugin  ->
            if (transformedValue == null) {
                transformedValue = plugin.transformToStringValue(value)
            }
        }

        val stringValue = transformedValue ?: throw CannotTransformStringValueError("No plugins you provided were able to set the value. Provide different plugins.")

        this.setValue(id, stringValue)
    }

    open override fun getStringValue(id: String): String? = valueOverrides[id]

    open override fun activate() {
    }

    open override fun refresh(onComplete: (Result<Unit>) -> Unit) {
        onComplete(Result.success(Unit))
    }

    class CannotTransformStringValueError(message: String): Throwable(message)

}
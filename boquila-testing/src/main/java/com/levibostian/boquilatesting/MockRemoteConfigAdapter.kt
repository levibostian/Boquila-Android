package com.levibostian.boquilatesting

import com.levibostian.boquila.BaseRemoteConfigAdapter
import com.levibostian.boquila.RemoteConfigAdapter
import com.levibostian.boquila.plugins.RemoteConfigAdapterPlugin

open class MockRemoteConfigAdapter(plugins: List<RemoteConfigAdapterPlugin> = listOf()): BaseRemoteConfigAdapter(plugins) {

    /**
    Stores the values you want to override in the mock.
     */
    open var valueOverrides: Map<String, String> = mapOf()

    /**
    Attempts to transform the value you pass into something else usable
     */
    open fun setValue(id: String, value: String) {
        val oldValueOverrides = this.valueOverrides.toMutableMap()

        oldValueOverrides[id] = value

        this.valueOverrides = oldValueOverrides
    }

    open fun <T: Any> setValue(id: String, value: T) {
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

    /**
     * By default, does not do anything. Override this method in your own subclass to do something.
     */
    open override fun activate() {
    }

    /**
     * By default, does not do anything. Override this method in your own subclass to do something.
     */
    open override fun refresh(onComplete: (Result<Unit>) -> Unit) {
        onComplete(Result.success(Unit))
    }

    class CannotTransformStringValueError(message: String): Throwable(message)

}
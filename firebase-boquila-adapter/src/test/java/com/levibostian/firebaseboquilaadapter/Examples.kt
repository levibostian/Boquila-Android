package com.levibostian.firebaseboquilaadapter

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.levibostian.boquila.RemoteConfigAdapter

/**
 * This file exists to store example usage of the adapter. It's not meant to actually run tests at this time.
 */
class Examples {

    val isDevelopment = true

    fun foo() {
        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        if (isDevelopment) {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
            firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        }

        val remoteConfigAdapter: RemoteConfigAdapter = FirebaseRemoteConfigAdapter(firebaseRemoteConfig, plugins = listOf())

        val optionalStringValue: String? = remoteConfigAdapter.getValue("optional_string_value")
        val nonoptionalStringValue: String? = remoteConfigAdapter.getValue("optional_string_value", defaultValue = "Default value")

        val nonStringValue: NonStringValue? = remoteConfigAdapter.getValue("non_string_value")

        // One of the suggested methods to refresh remote config values is to activate and then refresh *in that order* when your app starts.
        // Learn more: https://firebase.google.com/docs/remote-config/loading
        remoteConfigAdapter.activate()
        remoteConfigAdapter.refresh {}
    }

    data class NonStringValue(val foo: String)

}
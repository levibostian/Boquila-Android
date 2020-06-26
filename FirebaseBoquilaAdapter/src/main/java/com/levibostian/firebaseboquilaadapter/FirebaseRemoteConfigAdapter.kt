package com.levibostian.firebaseboquilaadapter

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.levibostian.boquila.BaseRemoteConfigAdapter
import com.levibostian.boquila.plugins.RemoteConfigAdapterPlugin

// Note: We are not taking in a `development` value that we use to set development mode on firebase remote config. that's because there is not a good way to *append* settings in Android's SDK. We can overwrite settings but we don't want to do that in a library.
class FirebaseRemoteConfigAdapter(private val firebaseRemoteConfig: FirebaseRemoteConfig, plugins: List<RemoteConfigAdapterPlugin> = emptyList()): BaseRemoteConfigAdapter(plugins) {

    override fun getStringValue(id: String): String? {
        val stringValue = firebaseRemoteConfig.getString(id)
        return if (stringValue == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_STRING) null
        else stringValue
    }

    override fun activate() {
        firebaseRemoteConfig.activateFetched()
    }

    override fun refresh(onComplete: (Result<Unit>) -> Unit) {
        firebaseRemoteConfig.fetch().addOnCompleteListener {
            val fetchError = it.exception
            if (fetchError != null) onComplete(Result.failure(fetchError))
            else onComplete(Result.success(Unit))
        }
    }

}
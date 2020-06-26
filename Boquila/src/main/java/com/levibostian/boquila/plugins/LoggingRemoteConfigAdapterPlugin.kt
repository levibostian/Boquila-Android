package com.levibostian.boquila.plugins

import android.util.Log

class LoggingRemoteConfigAdapterPlugin(private val tag: String = "[BOQUILA]"): RemoteConfigAdapterPlugin() {

    override fun manipulateStringValue(stringValue: String) : String {
        log("String value from remote config service: $stringValue")

        return stringValue
    }

    override fun activateBegin() {
        log("Activate remote config")
    }

    override fun refreshBegin() {
        log("Remote config refresh begin...")
    }

    override fun refreshEnd(result: Result<Unit>) {
        if (result.isSuccess) {
            log("Remote config refresh success!")
        } else {
            log("Remote config refresh error. Description: ${result.exceptionOrNull() ?: "(no exception)"}")
        }
    }

    private fun log(message: String) {
        Log.d(this.tag, message)
    }

}
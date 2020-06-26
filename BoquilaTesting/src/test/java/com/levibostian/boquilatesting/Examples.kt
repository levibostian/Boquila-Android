package com.levibostian.boquilatesting

import com.levibostian.boquila.RemoteConfigAdapter

/**
 * This file exists to store example usage of the adapter. It's not meant to actually run tests at this time.
 */
class Examples {

    val isDevelopment = true

    fun foo() {
        val adapter = MockRemoteConfigAdapter()
        adapter.setValue("contact-us-phone", "555-555-5555")
        // You can use dependency injection to inject the `adapter` instance into your code under test.
        // That way when your code under test now will call...
        val phoneNumber: String? = adapter.getValue("contact-us-phone")
        // ...they will get the value that you set!
    }

}
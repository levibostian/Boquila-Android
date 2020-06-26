# Testing

This Testing module is designed to be flexible in order to be used in any of your tests: unit, integration, or UI tests. 

# Getting started 

* Install the dependencies:

```
implementation 'com.levibostian.boquila:boquila:version-goes-here'
implementation 'com.levibostian.boquila:boquila-testing:version-goes-here'
```

Replace `version-here` with: [![Download](https://api.bintray.com/packages/levibostian/Boquila/com.levibostian.boquila/images/download.svg)](https://bintray.com/levibostian/Boquila/com.levibostian.boquila/_latestVersion) which is the latest version at this time.


* This testing module consists of 1 class at this time, `MockRemoteConfigAdapter`. This 1 class is very basic: it gets values from a local `Map` instead of using a real remote config service provider. This is similar to mocking network calls in Android testing. 

The `MockRemoteConfigAdapter` class does not do anything for other adapter functions like `activate()` and `refresh()`. If you need to perform actions here, override `MockRemoteConfigAdapter` and provide the functionality you need. If you have found a legitimate use case that should be built into the `MockRemoteConfigAdapter`, [create an issue](https://github.com/levibostian/boquila-android/issues/new) with your idea and it will be discussed there. 


```kotlin
val adapter = MockRemoteConfigAdapter()
adapter.setValue("contact-us-phone", "555-555-5555")
// You can use dependency injection to inject the `adapter` instance into your code under test.
// That way when your code under test now will call...
val phoneNumber: String? = adapter.getValue("contact-us-phone")
// ...they will get the value that you set!
```

##### Plugins

`MockRemoteConfigAdapter` is able to use plugins. It is recommended to use plugins if you use them for other adapters in your app. 
```kotlin
MockRemoteConfigAdapter(plugins = listOf(...))
```

*Note: If you call `mockAdapter.setValue()` with a value that is not a String, the `MockRemoteConfigAdapter` will use the plugins to try and transform the given value into a string. If the adapter is unsuccessful doing this, it will throw an error.*

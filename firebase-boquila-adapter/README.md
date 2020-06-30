# Firebase 

# Getting started 

* Install the dependencies:

```
implementation 'com.levibostian.boquila:boquila:version-goes-here'
implementation 'com.levibostian.boquila:firebase-boquila-adapter:version-goes-here'
```

Replace `version-here` with: [![Download](https://api.bintray.com/packages/levibostian/Boquila/com.levibostian.boquila/images/download.svg)](https://bintray.com/levibostian/Boquila/com.levibostian.boquila/_latestVersion) which is the latest version at this time.

As well as Firebase dependencies:

```
implementation 'com.google.firebase:firebase-config-ktx:19.1.4'
implementation 'com.google.firebase:firebase-analytics-ktx:17.4.3' // required for remote config
```

*Note: There may be more dependencies that Firebase requires and the version numbers above might be out-of-date. See [the official remote config guide](https://firebase.google.com/docs/remote-config/use-config-android) to get the most up-to-date information.*

* [Setup Firebase in your Android app](https://firebase.google.com/docs/android/setup) if you have not done so already. 

* Get the Firebase remote config singleton and configure it if needed:

```kotlin
val firebaseRemoteConfig = Firebase.remoteConfig
if (isDevelopment) {
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 0
    }
    firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
}
```

* Use the Adapter to get values from Firebase remote config.

```kotlin
val remoteConfigAdapter: RemoteConfigAdapter = FirebaseRemoteConfigAdapter(firebaseRemoteConfig, plugins = listOf())

val optionalStringValue: String? = remoteConfigAdapter.getValue("optional_string_value")
val nonoptionalStringValue: String? = remoteConfigAdapter.getValue("optional_string_value", defaultValue = "Default value")

val nonStringValue: NonStringValue? = remoteConfigAdapter.getValue("non_string_value", NonStringValue::class.java)

// One of the suggested methods to refresh remote config values is to activate and then refresh *in that order* when your app starts. 
// Learn more: https://firebase.google.com/docs/remote-config/loading
remoteConfigAdapter.activate()
remoteConfigAdapter.refresh {}
```
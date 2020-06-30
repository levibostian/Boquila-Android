# Moshi plugin

Use JSON strings for your remote config values and deserialize that JSON with [Moshi](https://github.com/square/moshi). 

# Getting started 

* Install the dependencies: 

```
implementation 'com.levibostian.boquila:boquila:version-goes-here'
implementation 'com.levibostian.boquila:moshi-boquila-plugin:version-goes-here'
```

Replace `version-here` with: [![Download](https://api.bintray.com/packages/levibostian/Boquila/com.levibostian.boquila/images/download.svg)](https://bintray.com/levibostian/Boquila/com.levibostian.boquila/_latestVersion) which is the latest version at this time.

As well as Moshi dependencies:

```
implementation "com.squareup.moshi:moshi:1.9.3"
```

*Note: There may be more dependencies that Moshi requires and the version numbers above might be out-of-date. See [the Moshi documentation](https://github.com/square/moshi#download) to get the most up-to-date information. If you are using Kotlin, you will especially need to [add more dependencies](https://github.com/square/moshi#kotlin)*

* From here, all you need to do is give your Moshi plugin to the remote config adapter that you are using. Let's go with an example below with using the `FirebaseRemoteConfigAdapter`:

```kotlin
val moshi = Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()

val remoteConfigAdapter: RemoteConfigAdapter = FirebaseRemoteConfigAdapter(firebaseRemoteConfig, plugins = listOf(MoshiRemoteConfigAdapterPlugin(moshi)))
```

* That's it! As long as you are using JSON strings as your remote config values, then Moshi will deserialize the JSON for you into objects. 

```kotlin
data class PlayingCard(val rank: String, val suit: String)

val playingCard = remoteConfigAdapter.getValue("playing_card", PlayingCard::class.java)
```
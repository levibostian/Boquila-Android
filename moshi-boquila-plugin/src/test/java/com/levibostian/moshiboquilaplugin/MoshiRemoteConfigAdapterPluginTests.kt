package com.levibostian.moshiboquilaplugin

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.levibostian.moshiboquilaplugin.test_data.PlayingCard
import com.squareup.moshi.Moshi
import org.junit.Test
import org.junit.Before

class MoshiRemoteConfigAdapterPluginTests {

    private lateinit var plugin: MoshiRemoteConfigAdapterPlugin

    @Before
    fun setup() {
        plugin = MoshiRemoteConfigAdapterPlugin(Moshi.Builder().build())
    }

    @Test
    fun transformStringValue_givenEmptyString_expectNull() {
        assertThat(plugin.transformStringValue(" ", PlayingCard::class.java)).isNull()
    }

    @Test
    fun transformStringValue_givenListOfValues_expectListOfObjects() {
        val stringValue = """
            [
              {
                "rank": "4",
                "suit": "Clubs"
              },
              {
                "rank": "Ace",
                "suit": "Hearts"
              }
            ]
        """.trimIndent()

        val playingCards: List<PlayingCard>? = plugin.transformStringValues(stringValue, PlayingCard::class.java)

        assertThat(playingCards).isEqualTo(listOf(PlayingCard("4", "Clubs"), PlayingCard("Ace", "Hearts")))
    }

    @Test
    fun transformStringValue_givenObject_expectObject() {
        val stringValue = """
              {
                "rank": "4",
                "suit": "Clubs"
              }
        """.trimIndent()

        val playingCard: PlayingCard? = plugin.transformStringValue(stringValue, PlayingCard::class.java)

        assertThat(playingCard).isEqualTo(PlayingCard("4", "Clubs"))
    }

}
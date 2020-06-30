package com.levibostian.moshiboquilaplugin.test_data;

import java.util.Objects;

/*
Using a Java class here instead of a Kotlin class because Kotlin requires reflection or code generation plugins with Moshi while Java does not. We want to try and keep this library slim so using a couple Java classes for testing purposes is not bad.
 */
public class PlayingCard {
    public String rank;
    public String suit;

    public PlayingCard(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCard that = (PlayingCard) o;
        return rank.equals(that.rank) &&
                suit.equals(that.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

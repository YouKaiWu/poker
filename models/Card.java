package models;

public class Card implements Comparable<Card> {
    public static enum Suit {
        SPADE, HEART, DIAMOND, CLUB
    }

    private Suit suit;
    private int face;

    public Card(Suit suit, int face) {
        this.suit = suit;
        this.face = face;
    }

    @Override
    public int compareTo(Card card) {
        if (this.face == card.face) {
            return Integer.compare(this.suit.ordinal(), card.suit.ordinal());
        }
        return Integer.compare(this.face, card.face);
    }
}

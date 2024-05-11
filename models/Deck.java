package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Deck implements Iterable<Card> {
    private ArrayList<Card> cards;

    public Deck(Iterable<Card> cards) {
        this.cards = new ArrayList<>();
        for (Card card : cards) {
            this.cards.add(card);
        }
    }
    
    public Deck(Card[] cards) {
        this.cards = new ArrayList<>();
        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void remove(Card card) {
        cards.remove(card);
    }

    public void remove(int index) {
        cards.remove(index);
    }

    public int size() {
        return cards.size();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}

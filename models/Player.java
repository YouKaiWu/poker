package models;

import java.util.*;

public class Player {
    private String name;
    private int chips;
    private List<Card> hand;
    private int currentBet;
    private boolean active;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
        this.hand = new ArrayList<>();
        this.currentBet = 0;
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void placeBet(int amount) {
        currentBet += amount;
        chips -= amount;
    }

    public void resetBet() {
        currentBet = 0;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public void receiveChips(int amount) {
        chips += amount;
    }
}

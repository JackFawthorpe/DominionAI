package dominion.core.player;

import dominion.core.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for the data relating to a player
 */
public class Player {
    private String name;
    private int money;
    private int actions;
    private int buys;

    private ArrayList<Card> toDraw;
    private ArrayList<Card> hand;

    private ArrayList<Card> discardPile;

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getActions() {
        return actions;
    }

    public int getBuys() {
        return buys;
    }

    public List<Card> getToDraw() {
        return toDraw;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }
}

package dominion.core.player;

import dominion.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for the data relating to a player
 */
public class Player {

    private final String name;
    private final ArrayList<Card> toDraw;
    private final ArrayList<Card> hand;
    private final ArrayList<Card> discardPile;
    private int money;
    private int actions;
    private int buys;

    public Player(String name) {
        this.name = name;
        this.money = 0;
        this.actions = 0;
        this.buys = 0;
        toDraw = new ArrayList<>();
        hand = new ArrayList<>();
        discardPile = new ArrayList<>();
    }

    /**
     * Updates the players core turn resources
     *
     * @param actions The amount to increment actions
     * @param buys    The amount to increment buys
     * @param money   The amount to increment money
     */
    public void updateTurnResources(int actions, int buys, int money) {
        this.actions += actions;
        this.buys += buys;
        this.money += money;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Player player
                && player.name.equals(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

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

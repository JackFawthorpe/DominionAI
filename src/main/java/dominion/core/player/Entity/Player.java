package dominion.core.player.Entity;

import dominion.card.Card;

import java.util.List;

/**
 * Entity class for the data relating to a player
 */
public class Player {

    private final String name;
    private final PlayerDeck deck;
    private int money;
    private int actions;
    private int buys;

    private int id;

    public Player(String name, int id) {
        this.id = id;
        this.name = name;
        this.money = 0;
        this.actions = 0;
        this.buys = 0;
        this.deck = new PlayerDeck(this);
    }

    public int getId() {
        return id;
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

    /**
     * Resets the players turn resources to what they start with each turn
     */
    public void resetTurnResources() {
        this.actions = 1;
        this.buys = 1;
        this.money = 0;
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

    public PlayerDeck getDeck() {
        return deck;
    }

    /**
     * Checks whether the player has any cards in their hand
     *
     * @return true if they have any cards in their hand
     */
    public boolean isHandEmpty() {
        return deck.getHand().isEmpty();
    }

    /**
     * Gets the total amount of points the player has
     *
     * @return The number of victory points in the deck
     */
    public int getPoints() {
        return deck.getAllCards().stream()
                .map(Card::getVictoryPoints)
                .reduce(Integer::sum).orElse(0);
    }

    /**
     * Accessor method for getting all the cards belonging to a player
     *
     * @return The list of cards in the players deck
     */
    public List<Card> getCards() {
        return deck.getAllCards();
    }
}

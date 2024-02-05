package dominion.card;

import dominion.core.player.Entity.Player;

/**
 * Abstract class to represent the common functionality of each card
 */
public abstract class Card implements Cloneable {
    private int actions;
    private int buys;
    private int money;
    private int victoryPoints;
    private Player owner;
    private String name;

    private int cost;

    private CardType cardType;

    /**
     * Base card
     */
    protected Card() {
        this.actions = 0;
        this.buys = 0;
        this.money = 0;
        this.victoryPoints = 0;
        this.name = "Default Name";
    }

    /**
     * Activates the abilities of the card on the owner of the card.
     * At its simplest this represents updating the players turn resources,
     * for cards with more complicated abilities they are able to hook in within the child classes
     * through the use of the {@link #playCardHook()}}
     */
    public void playCard() {
        owner.updateTurnResources(actions, buys, money);
        playCardHook();
    }

    /**
     * Empty Hook for child classes to override with custom implementation of taking an action
     */
    protected void playCardHook() {
        // To be overridden primarily by action cards
    }

    // Builder methods to be used in implementations of the Card class

    protected void withVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    protected void withName(String name) {
        this.name = name;
    }

    public int getActions() {
        return actions;
    }

    public int getBuys() {
        return buys;
    }

    public int getMoney() {
        return money;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public int getCost() {
        return cost;
    }

    public CardType getCardType() {
        return cardType;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Card card
                && card.getName().equals(this.name)
                && card.getOwner().equals(this.owner);
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    @Override
    public Card clone() {
        try {
            Card clone = (Card) super.clone();
            clone.setOwner(null);
            clone.withActions(this.actions);
            clone.withBuys(this.buys);
            clone.withBuys(this.buys);
            clone.withMoney(this.money);
            clone.withCost(this.cost);
            clone.withCardType(this.cardType);
            return clone;
        } catch (Exception e) {
            throw new RuntimeException("Failed to clone card. This shouldn't happen");
        }
    }

    protected void withActions(int actions) {
        this.actions = actions;
    }

    protected void withBuys(int buys) {
        this.buys = buys;
    }

    protected void withMoney(int money) {
        this.money = money;
    }

    protected void withCost(int cost) {
        this.cost = cost;
    }

    protected void withCardType(CardType cardType) {
        this.cardType = cardType;
    }
}

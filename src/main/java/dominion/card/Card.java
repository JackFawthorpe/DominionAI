package dominion.card;

import dominion.core.player.Player;

/**
 * Abstract class to represent the common functionality of each card
 */
public abstract class Card {
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

    protected void withActions(int actions) {
        this.actions = actions;
    }

    protected void withBuys(int buys) {
        this.buys = buys;
    }

    protected void withMoney(int money) {
        this.money = money;
    }

    protected void withVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    protected void withName(String name) {
        this.name = name;
    }

    protected void withCost(int cost) {
        this.cost = cost;
    }

    protected void withCardType(CardType cardType) {
        this.cardType = cardType;
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

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public CardType getCardType() {
        return cardType;
    }
}

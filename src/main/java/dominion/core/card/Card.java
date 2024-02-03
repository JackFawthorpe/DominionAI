package dominion.core.card;

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

    // Builder methods to be used in implementations of the Card class

    protected Card withActions(int actions) {
        this.actions = actions;
        return this;
    }

    protected Card withBuys(int buys) {
        this.buys = buys;
        return this;
    }
    protected Card withMoney(int money) {
        this.money = money;
        return this;
    }
    protected Card withVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
        return this;
    }
    protected Card withName(String withName) {
        this.name = withName;
        return this;
    }

    public void setOwner(Player player) {
        this.owner = player;
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

    public String getName() {
        return name;
    }
}

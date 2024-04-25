package dominion.card;

import api.data.CardData;
import api.data.CardName;
import dominion.core.geb.GameEventBus;
import dominion.core.geb.event.PlayCardEvent;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.DrawCardRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to represent the common functionality of each card
 */
public abstract class Card implements Cloneable {
    protected Player player;
    private List<CardType> cardType;
    private int actions;
    private int buys;
    private int money;
    private int victoryPoints;
    private String name;
    private int cost;

    private int drawCount;

    /**
     * Base card
     */
    protected Card() {
        this.actions = 0;
        this.buys = 0;
        this.money = 0;
        this.victoryPoints = 0;
        this.drawCount = 0;
        this.name = "Default Name";
        this.cardType = new ArrayList<>();
    }

    /**
     * Activates the abilities of the card on the owner of the card.
     * At its simplest this represents updating the players turn resources,
     * for cards with more complicated abilities they are able to hook in within the child classes
     * through the use of the {@link #playCardHook()}}
     */
    public void playCard() {
        player.updateTurnResources(actions, buys, money);
        if (drawCount != 0) {
            DrawCardRequest drawCardRequest = new DrawCardRequest(player, drawCount);
            drawCardRequest.execute();
        }
        playCardHook();
        GameEventBus.getInstance().notifyListeners(new PlayCardEvent(this));
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

    public List<CardType> getCardType() {
        return cardType;
    }

    public boolean isType(CardType cardType) {
        return this.cardType.contains(cardType);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Card card
                && card.getName().equals(this.name)
                && card.getPlayer().equals(this.player);
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Card clone() {
        try {
            Card clone = (Card) super.clone();
            clone.setPlayer(null);
            clone.withActions(this.actions);
            clone.withBuys(this.buys);
            clone.withBuys(this.buys);
            clone.withMoney(this.money);
            clone.withCost(this.cost);
            clone.withCardType(new ArrayList<>(this.cardType));
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

    /**
     * Removes the current List of card type and replaces them with the new list
     *
     * @param cardType The list of card types to apply to the card
     */
    protected void withCardType(List<CardType> cardType) {
        this.cardType = cardType;
    }

    public int getDrawCount() {
        return drawCount;
    }

    /**
     * Adds the card type to the list of types of the card
     *
     * @param cardType The type to add
     */
    protected void withCardType(CardType cardType) {
        this.cardType.add(cardType);
    }

    protected void withSimpleDraw(int drawCount) {
        this.drawCount = drawCount;
    }

    /**
     * Turns the interactive card to a read only object
     *
     * @return The readonly version of the card
     */
    public CardData toCardData() {
        return new CardData(
                this.cardType,
                this.actions,
                this.buys,
                this.money,
                this.victoryPoints,
                CardName.valueOf(this.name.toUpperCase().replace(" ", "")),
                this.cost,
                this.drawCount
        );
    }

    /**
     * Turns the string representation of the card name and switch it to the enum
     *
     * @return The enum representation of the card name
     */
    public CardName getCardName() {
        return CardName.valueOf(this.name.toUpperCase().replace(" ", ""));
    }
}

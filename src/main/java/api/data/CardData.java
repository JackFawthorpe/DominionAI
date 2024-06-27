package api.data;

import dominion.card.CardType;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * This class represents the data that is about a card such as how much it costs or what card it is
 */
@EqualsAndHashCode
public class CardData {
    private final List<CardTypeData> cardType;
    private final int actions;
    private final int buys;
    private final int money;
    private final int victoryPoints;
    private final CardName name;
    private final int cost;

    private final int drawCount;

    public CardData(List<CardType> cardTypes, int actions, int buys, int money, int victoryPoints, CardName name, int cost, int drawCount) {
        this.cardType = cardTypes.stream().map(CardType::toData).toList();
        this.actions = actions;
        this.buys = buys;
        this.money = money;
        this.victoryPoints = victoryPoints;
        this.name = name;
        this.cost = cost;
        this.drawCount = drawCount;
    }

    /**
     * returns a list of all the applicable card types of a card. Includes types such as action, attack and victory
     */
    public List<CardTypeData> getCardTypes() {
        return cardType;
    }

    /**
     * Playing this card will give this many actions to the player
     */
    public int getActions() {
        return actions;
    }

    /**
     * Playing this card will allow the player to buy this many more cards this turn
     */
    public int getBuys() {
        return buys;
    }

    /**
     * Playing this card will give the player this much money on their turn
     */
    public int getMoney() {
        return money;
    }

    /**
     * This will return the amount of victory points this card is worth
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * This will return an enum value of the card played
     */
    public CardName getName() {
        return name;
    }

    /**
     * This is how much it costs to buy the card
     */
    public int getCost() {
        return cost;
    }

    /**
     * When played the player will draw this many cards
     */
    public int getDrawCount() {
        return drawCount;
    }
}

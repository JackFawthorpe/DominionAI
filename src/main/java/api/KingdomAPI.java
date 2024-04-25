package api;

import api.data.CardData;
import api.data.CardName;
import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.core.state.KingdomManager;

import java.util.List;

/**
 * API for interacting with the cards that aren't owned by anybody
 */
public class KingdomAPI {

    /**
     * Usage: KingdomAPI.getDepletedCount()
     * Returns the number of piles that no longer have any cards
     * <p>
     * Note: The game ends when this reaches 3 or the province pile runs out of cards
     */
    public static int getDepletedCount() {
        return KingdomManager.getInstance().getDepletedSupplyPileCount();
    }


    /**
     * Usage: KingdomAPI.getDepletedCount(CardName.COPPER)
     * Returns how many of the card provided are left
     */
    public static int getCardCountRemaining(CardName cardName) {
        return KingdomManager.getInstance().getCardCountRemaining(cardName);
    }

    /**
     * Usage: KingdomAPI.getAvailableCards()
     * Returns a list of all the cards that are currently available for purchase
     */
    public static List<CardData> getAvailableCards() {
        return KingdomManager.getInstance()
                .getAvailableCards(new CardSpecification())
                .stream().map(Card::toCardData).toList();
    }

}

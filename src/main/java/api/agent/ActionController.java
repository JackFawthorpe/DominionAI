package api.agent;

import api.data.CardData;

import java.util.List;

/**
 * This is the starting point of your controller.
 * <p>
 * You must implement each of these methods using your own unique strategy!
 * <p>
 * Rules of thumb:
 * - You can always assume there is at least one option within the list
 * - You can do nothing by returning null
 * - You must do something when the isRequired boolean is true
 */
public interface ActionController {
    /**
     * Buying a card is spending the cost of the card (card.getCost()) to buy the card
     * <p>
     * Return the card that you want your agent to buy or null if you don't want a new card
     */
    CardData buyCardHook(List<CardData> buyOptions);

    /**
     * Discarding is taking a card from your hand and putting it into the discard pile\
     * <p>
     * 1. Check if you are required to discard
     * 2. Decide if you want to discard a card from your hand
     * 3. Return null if you dont want to discard or return the card that you want to discard
     */
    CardData discardFromHandHook(List<CardData> discardOptions, boolean isRequired);

    /**
     * Gaining is taking a card from the kingdom without paying for it
     * <p>
     * Return the card that you want to gain from the list or null if you don't want a new card
     */
    CardData gainCardHook(List<CardData> gainOptions);

    /**
     * Trashing a card is removing the card from your deck permanently
     * <p>
     * Return the card that you want to trash from the list or null if you don't want a new card
     */
    CardData trashCardHook(List<CardData> trashOptions, boolean isRequired);

    /**
     * Topdeck a card is taking a card from your hand and putting it at the top of your deck. This means you
     * will draw it next turn
     * <p>
     * Return the card that you want to topdeck from the list or null if you don't want a new card
     */
    CardData chooseTopDeckHook(List<CardData> topDeckOptions, boolean required);

    /**
     * Gets a random card from the list
     * <p>
     * This should be used as a placeholder method as it performs quite poorly against simple ideas
     */
    CardData playActionCardHook(List<CardData> actionCardsInHand);
}

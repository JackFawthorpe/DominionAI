package dominion.core.player.controller;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Player;

/**
 * Placeholder controller for the actions that a player can take on their turn
 */
public class DefaultController extends PlayerController {

    /**
     * Registers the player for within the RequestForActionRouter to receive actions for the player
     *
     * @param player The player that this controller is responsible for controlling
     */
    public DefaultController(Player player) {
        super(player);
    }

    /**
     * Default Choose Action Handling,
     *
     * @return It will return the first action card in the hand (array-based) or null if there is no card
     */
    protected Card chooseActionHook() {
        for (Card card : player.getHand()) {
            if (card.getCardType() == CardType.ACTION) {
                return card;
            }
        }
        return null;
    }
}

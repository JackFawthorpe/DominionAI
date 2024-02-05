package dominion.core.player.controller;

import dominion.card.Card;
import dominion.core.player.Entity.Player;

import java.util.List;

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
    @Override
    protected Card playActionCardHook(List<Card> actionCardsInHand) {
        return actionCardsInHand.isEmpty() ? null : actionCardsInHand.get(0);
    }

    /**
     * Default Purchasing behaviour (This will return the first card within the list
     *
     * @return It will return the first action card in the hand (array-based) or null if there is no card
     */
    @Override
    protected Card buyCardHook(List<Card> buyOptions) {
        return buyOptions.isEmpty() ? null : buyOptions.get(0);
    }
}

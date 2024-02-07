package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;

/**
 * Requests the player to discard one of the cards in their hand
 */
public class DiscardFromHandRequest extends ControllerActionRequest<Card> {
    /**
     * Overridden constructor for a request
     *
     * @param player   The player the request will be sent to
     * @param required Specifies if the discard action is optional for the player
     */
    public DiscardFromHandRequest(Player player, boolean required) {
        super(player, required);
    }
}

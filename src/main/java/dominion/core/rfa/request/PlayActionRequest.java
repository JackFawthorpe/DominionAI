package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.core.player.Player;
import dominion.core.rfa.ControllerActionRequest;

/**
 * Requests the player to pick and play an action card
 */
public class PlayActionRequest extends ControllerActionRequest<Card> {
    public PlayActionRequest(Player player) {
        super(player);
    }
}

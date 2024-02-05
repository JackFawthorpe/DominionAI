package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;

/**
 * Represents requesting the player to buy a card
 */
public class BuyCardRequest extends ControllerActionRequest<Card> {

    public BuyCardRequest(Player player) {
        super(player, false);
    }
}

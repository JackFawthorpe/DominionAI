package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;

import java.util.List;

/**
 * Action to represent asking the player to draw a card
 */
public class DrawCardRequest extends ControllerActionRequest<List<Card>> {

    private final int drawCount;

    /**
     * Overridden constructor for a request
     *
     * @param player The player the request will be sent to
     */
    public DrawCardRequest(Player player, int drawCount) {
        super(player, true);
        this.drawCount = drawCount;
    }

    public int getDrawCount() {
        return drawCount;
    }
}

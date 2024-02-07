package dominion.core.rfa.request;

import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;

/**
 * Request for the player to clean up their inventory
 */
public class CleanupRequest extends ControllerActionRequest<Void> {
    /**
     * Overridden constructor for a request
     *
     * @param player The player the request will be sent to
     */
    public CleanupRequest(Player player) {
        super(player, false);
    }
}

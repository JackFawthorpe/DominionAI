package dominion.core.player;

import dominion.core.rfa.PlayerActionRequest;
import dominion.core.rfa.PlayerActionRequestResponse;
import dominion.core.rfa.RequestForActionRouter;

/**
 * Represents the object that will control the player etc. AI, CLI player etc
 */
public abstract class PlayerController {
    Player player;

    /**
     * Handles incoming requests for the player
     * @param playerActionRequest The request for the player to fulfill
     * @return The response generated for the action
     */
    public abstract PlayerActionRequestResponse handleAction(PlayerActionRequest playerActionRequest);

    /**
     * Registers the player for within the RequestForActionRouter to receive actions for the player
     * @param player The player that this controller is responsible for controlling
     */
    protected PlayerController(Player player) {
        this.player = player;
        RequestForActionRouter.getInstance().addHandler(this, player);
    }
}

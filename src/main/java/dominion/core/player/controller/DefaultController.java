package dominion.core.player.controller;

import dominion.core.player.Player;

/**
 * Placeholder controller for characters that always takes the simplest action at any given moment
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
}

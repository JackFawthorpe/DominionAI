package dominion.core.player.controller;

import dominion.core.player.Player;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.rfa.request.PlayerActionRequest;
import dominion.core.rfa.response.ChooseActionResponse;
import dominion.core.rfa.response.PlayerActionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the object that will control the player etc. AI, CLI player etc
 */
public abstract class PlayerController {

    private static final Logger logger = LogManager.getLogger(PlayerController.class);

    Player player;

    /**
     * Registers the player for within the RequestForActionRouter to receive actions for the player
     *
     * @param player The player that this controller is responsible for controlling
     */
    protected PlayerController(Player player) {
        this.player = player;
        RequestForActionRouter.getInstance().addHandler(this, player);
    }

    /**
     * Handles incoming requests for the player
     *
     * @param playerActionRequest The request for the player to fulfill
     * @return The response generated for the action
     */
    public PlayerActionResponse handleAction(PlayerActionRequest playerActionRequest) {

        return switch (playerActionRequest.getCode()) {
            case CHOOSE_ACTION_REQUEST -> handleChooseAction(playerActionRequest.getArguments());
        };
    }

    public PlayerActionResponse handleChooseAction(Object... args) {
        logger.info("Player {} received request to choose an action card", player.getName());

        return chooseActionHook();
    }

    private PlayerActionResponse chooseActionHook(Object... args) {
        return new ChooseActionResponse(null);
    }
}

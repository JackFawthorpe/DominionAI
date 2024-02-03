package dominion.core.player.controller;

import dominion.card.Card;
import dominion.core.player.Player;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.rfa.request.PlayerActionRequest;
import dominion.core.rfa.response.PlayActionCardResponse;
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
    public PlayerActionResponse<?> handleAction(PlayerActionRequest playerActionRequest) {

        return switch (playerActionRequest.getCode()) {
            case PLAY_ACTION_CARD -> handlePlayActionCard();
        };
    }

    /**
     * Handles the process of calling the controller to pick a card to play and proceeds to play the card
     *
     * @return The action response to send back to RFA, null represents no card played otherwise, card played
     */
    public PlayActionCardResponse handlePlayActionCard() {
        logger.info("Player {} received request to choose an action card", player.getName());
        Card card = chooseActionHook();
        if (card == null) return new PlayActionCardResponse(null);
        card.playCard();
        return new PlayActionCardResponse(card);
    }

    /**
     * Default Choose Action Handling,
     *
     * @return It will return the first action card in the hand (array-based) or null if there is no card
     */
    protected abstract Card chooseActionHook();
}

package dominion.core.player.controller;

import dominion.card.Card;
import dominion.core.player.Player;
import dominion.core.player.PlayerDeck;
import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.rfa.request.CleanupRequest;
import dominion.core.rfa.request.PlayActionRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Represents the object that will control the player etc. AI, CLI player etc
 */
public abstract class PlayerController {

    private static final Logger logger = LogManager.getLogger(PlayerController.class);

    protected final Player player;

    protected final PlayerDeck deck;

    /**
     * Registers the player for within the RequestForActionRouter to receive actions for the player
     *
     * @param player The player that this controller is responsible for controlling
     */
    protected PlayerController(Player player) {
        this.player = player;
        this.deck = player.getDeck();
        RequestForActionRouter.getInstance().addHandler(this, player);
    }

    /**
     * Handles incoming requests for the player
     *
     * @param controllerActionRequest The request for the player to fulfill
     */
    public final void handleAction(ControllerActionRequest<?> controllerActionRequest) {
        if (controllerActionRequest instanceof PlayActionRequest request) {
            request.setResponse(handlePlayActionCard());
        } else if (controllerActionRequest instanceof CleanupRequest) {
            handleDeckCleanup();
        }
    }

    /**
     * Handles the process of calling the controller to pick a card to play and proceeds to play the card
     *
     * @return The action response to send back to RFA, null represents no card played otherwise, card played
     */
    private Card handlePlayActionCard() {
        logger.info("Player {} received request to choose an action card", player.getName());

        Card chosenCard = playActionCardHook(deck.getHandActionCards());
        if (chosenCard == null) return null;
        chosenCard.playCard();
        if (!deck.playCard(chosenCard)) {
            logger.error("Player {} played {} when they did not have it within their hand", player.getName(), chosenCard.getName());
            throw new IllegalStateException("Illegal move detected. Exiting game");
        } else {
            logger.info("Player {} played the card {}", player.getName(), chosenCard.getName());
        }
        return chosenCard;
    }

    /**
     * End of turn function for the players hand being reset for their next turn
     */
    private void handleDeckCleanup() {
        logger.info("Cleaning up deck of player {}", player.getName());
        deck.cleanUp();
    }


    /// API Interface : These methods are to be extended for each controller of a player

    /**
     * Default Choose Action Handling,
     *
     * @return It will return the first action card in the hand (array-based) or null if there is no card
     */
    protected abstract Card playActionCardHook(List<Card> actionCardsInHand);
}

package dominion.core.player.controller;

import dominion.card.Card;
import dominion.core.exception.IllegalMoveException;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.player.Entity.PlayerDeck;
import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.rfa.request.*;
import dominion.core.state.KingdomManager;
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
        } else if (controllerActionRequest instanceof BuyCardRequest request) {
            request.setResponse(handleBuyCard());
        } else if (controllerActionRequest instanceof CleanupRequest) {
            handleDeckCleanup();
        } else if (controllerActionRequest instanceof DiscardFromHandRequest request) {
            request.setResponse(handleDiscardFromHand(request));
        } else if (controllerActionRequest instanceof DrawCardRequest request) {
            handleDrawCard(request.getDrawCount());
        } else if (controllerActionRequest instanceof GainCardRequest request) {
            request.setResponse(handleGainCardRequest(request));
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
        if (chosenCard == null) {
            logger.info("Player {} chose to not play a card", player.getName());
            return null;
        }
        chosenCard.playCard();
        if (!deck.playCard(chosenCard)) {
            logger.error("Player {} played {} when they did not have it within their hand", player.getName(), chosenCard.getName());
            throw new IllegalMoveException("Illegal move detected. Exiting game");
        } else {
            logger.info("Player {} played the card {}", player.getName(), chosenCard.getName());
            player.updateTurnResources(-1, 0, 0);
        }
        return chosenCard;
    }

    /**
     * Processes a buy request, it first fetches the cards available to the player then prompts the implementation to
     * pick one of those cards and then adds it to the players deck
     *
     * @return The card which the player purchased
     */
    private Card handleBuyCard() {
        logger.info("Player {} received request to buy a card", player.getName());
        KingdomManager kingdomManager = KingdomManager.getInstance();
        List<Card> buyOptions = kingdomManager.getAvailableCards(player.getMoney());
        Card card = buyCardHook(buyOptions);
        logger.info("Player {} has chosen to buy a {}", player.getName(), card.getName());
        kingdomManager.removeCard(card);
        deck.addCard(card, DeckPosition.DISCARD);
        player.updateTurnResources(0, -1, -card.getCost());
        return card;
    }

    /**
     * End of turn function for the players hand being reset for their next turn
     */
    private void handleDeckCleanup() {
        logger.info("Cleaning up deck of player {}", player.getName());
        deck.cleanUp();
    }

    /**
     * Processes a discard request, it first fetches the cards available to the player then prompts the implementation to
     * pick one of those cards and then adds it to the players deck
     *
     * @return The card which the player purchased
     */
    private Card handleDiscardFromHand(DiscardFromHandRequest request) {
        logger.info("Player {} received a request to discard a card", player.getName());
        List<Card> discardOptions = player.getDeck().getHand();
        Card card = discardFromHandHook(discardOptions, request.isRequired());
        if (card == null && request.isRequired() && !discardOptions.isEmpty()) {
            logger.error("Player {} failed to discard a card when it was both required and possible", player.getName());
            throw new IllegalMoveException("Illegal move detected, exiting game");
        } else if (card == null) {
            logger.info("Player {} chose not to discard a card", player.getName());
        } else {
            deck.moveCard(card, DeckPosition.HAND, DeckPosition.DISCARD);
            logger.info("Player {} has chosen to discard {}", player.getName(), card.getName());
        }
        return card;
    }

    /**
     * Processes a draw card request
     *
     * @param drawCount The amount of cards the player needs to draw
     */
    private void handleDrawCard(int drawCount) {
        logger.info("Player {} is drawing {} cards", player.getName(), drawCount);
        deck.draw(drawCount);
    }

    /**
     * Processes a gain card request
     *
     * @param request The request to execute
     */
    private Card handleGainCardRequest(GainCardRequest request) {
        logger.info("Player {} received a request to gain a card", player.getName());
        Card cardToGain = gainCardHook(request.getGainOptions());
        if (request.isRequired() && cardToGain == null && !(request.getGainOptions().isEmpty())) {
            logger.error("Player {} failed to gain a card when it was both required and possible", player.getName());
            throw new IllegalMoveException("Illegal move detected, exiting game");
        } else if (cardToGain == null) {
            logger.info("Player {} chose not to gain a card", player.getName());
        } else {
            KingdomManager.getInstance().removeCard(cardToGain);
            deck.addCard(cardToGain, request.getGainPosition());
        }
        return cardToGain;
    }


    /// API Interface : These methods are to be extended for each controller of a player

    /**
     * Hook to allow the player to choose which action they want to do
     *
     * @param actionCardsInHand The cards that are actionable. (The actions currently in their hand)
     * @return The card the player wants to play
     */
    protected abstract Card playActionCardHook(List<Card> actionCardsInHand);

    /**
     * Hook to allow for the player to choose which card they want to buy
     *
     * @param buyOptions The cards that the player can currently buy
     * @return The card the player wants to buy
     */
    protected abstract Card buyCardHook(List<Card> buyOptions);

    /**
     * Hook to allow for the player to choose which card they want to discard from their hand
     *
     * @param discardOptions The cards in their hand
     * @return The card the player wants to discard
     */
    protected abstract Card discardFromHandHook(List<Card> discardOptions, boolean isRequired);

    /**
     * Hook to allow for the player to choose which card they want to gain
     *
     * @param gainOptions The cards the play has to choose from
     * @return The card that the player chooses to gain
     */
    protected abstract Card gainCardHook(List<Card> gainOptions);
}

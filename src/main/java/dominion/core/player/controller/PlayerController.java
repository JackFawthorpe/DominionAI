package dominion.core.player.controller;

import api.agent.ActionController;
import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
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
public class PlayerController {

    private static final Logger logger = LogManager.getLogger(PlayerController.class);

    private final Player player;
    private final PlayerDeck deck;
    private final ActionController actionController;

    /**
     * Registers the player for within the RequestForActionRouter to receive actions
     * for the player
     *
     * @param player The player that this controller is responsible for controlling
     */
    public PlayerController(Player player, ActionController actionController) {
        this.player = player;
        this.deck = player.getDeck();
        this.actionController = actionController;
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
            request.setResponse(handleDrawCard(request.getDrawCount()));
        } else if (controllerActionRequest instanceof GainCardRequest request) {
            request.setResponse(handleGainCardRequest(request));
        } else if (controllerActionRequest instanceof TrashCardRequest request) {
            request.setResponse(handleTrashCard(request));
        } else if (controllerActionRequest instanceof MoveCardRequest request) {
            handleMoveCard(request);
        } else if (controllerActionRequest instanceof TopDeckRequest request) {
            request.setResponse(handleTopDeckRequest(request));
        }
    }

    /**
     * Handles the process of calling the controller to pick a card to play and
     * proceeds to play the card
     *
     * @return The action response to send back to RFA, null represents no card
     * played otherwise, card played
     */
    private Card handlePlayActionCard() {
        logger.info("Player {} received request to choose an action card", player.getName());
        Card chosenCard = actionController.playActionCardHook(
                deck.getCards(DeckPosition.HAND, new CardSpecification().withType(CardType.ACTION)));
        if (chosenCard == null) {
            logger.info("Player {} chose to not play a card", player.getName());
            return null;
        }
        if (!deck.moveCard(chosenCard, DeckPosition.HAND, DeckPosition.PLAYED)) {
            logger.error("Player {} played {} when they did not have it within their hand", player.getName(),
                    chosenCard.getName());
            throw new IllegalMoveException("Illegal move detected. Exiting game");
        } else {
            // Plays card after moving, when resolving effects,
            // the played card is treated as though its in the played pile, not the deck
            logger.info("Player {} is playing the card {}", player.getName(), chosenCard.getName());
            chosenCard.playCard();
            player.updateTurnResources(-1, 0, 0);
        }
        return chosenCard;
    }

    /**
     * Processes a buy request, it first fetches the cards available to the player
     * then prompts the implementation to
     * pick one of those cards and then adds it to the players deck
     *
     * @return The card which the player purchased
     */
    private Card handleBuyCard() {
        updateMoney();
        logger.info("Player {} received request to buy a card", player.getName());
        KingdomManager kingdomManager = KingdomManager.getInstance();
        List<Card> buyOptions = kingdomManager
                .getAvailableCards(new CardSpecification().withMaxCost(player.getMoney()));
        Card card = actionController.buyCardHook(buyOptions);
        if (card == null) {
            logger.info("Player {} has chosen not to buy a card", player.getName());
            return null;
        }
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
     * Processes a discard request, it first fetches the cards available to the
     * player then prompts the implementation to
     * pick one of those cards and then adds it to the players deck
     *
     * @return The card which the player purchased
     */
    private Card handleDiscardFromHand(DiscardFromHandRequest request) {
        logger.info("Player {} received a request to discard a card", player.getName());
        List<Card> discardOptions = player.getDeck().getHand();
        Card card = actionController.discardFromHandHook(discardOptions, request.isRequired());
        if (card == null && request.isRequired() && !discardOptions.isEmpty()) {
            logger.error("Player {} failed to discard a card when it was both required and possible", player.getName());
            throw new IllegalMoveException("Illegal move detected, exiting game");
        } else if (card == null) {
            logger.info("Player {} chose not to discard a card", player.getName());
        } else if (!deck.moveCard(card, DeckPosition.HAND, DeckPosition.DISCARD)) {
            logger.error("Attempted to discard {} from players hand but it wasn't in their hand", player.getName());
            throw new IllegalMoveException("Illegal move detected, exitting game");
        } else {
            logger.info("Player {} has chosen to discard {}", player.getName(), card.getName());
        }
        return card;
    }

    /**
     * Processes a draw card request
     *
     * @param drawCount The amount of cards the player needs to draw
     * @return An unmodifiable list of the cards that were drawn
     */
    private List<Card> handleDrawCard(int drawCount) {
        logger.info("Player {} is drawing {} cards", player.getName(), drawCount);
        return deck.draw(drawCount);
    }

    /**
     * Processes a gain card request
     *
     * @param request The request to execute
     */
    private Card handleGainCardRequest(GainCardRequest request) {
        logger.info("Player {} received a request to gain a card", player.getName());
        List<Card> gainOptions = KingdomManager.getInstance().getAvailableCards(request.getCardSpecification());
        Card card = actionController.gainCardHook(gainOptions);
        if (request.isRequired() && card == null && !(gainOptions.isEmpty())) {
            logger.error("Player {} failed to gain a card when it was both required and possible", player.getName());
            throw new IllegalMoveException("Illegal move detected, exiting game");
        } else if (card == null) {
            logger.info("Player {} chose not to gain a card", player.getName());
        } else {
            KingdomManager.getInstance().removeCard(card);
            deck.addCard(card, request.getGainPosition());
        }
        return card;
    }

    /**
     * Processes a trash card request
     *
     * @param request The trash card request
     * @return The card that the player chose to trash
     */
    private Card handleTrashCard(TrashCardRequest request) {
        logger.info("Player {} received a request to trash a card", player.getName());
        List<Card> cardsInPosition = deck.getCards(request.getDeckPosition());
        List<Card> trashOptions = request.getCardSpecification().filterCards(cardsInPosition);
        Card card = actionController.trashCardHook(trashOptions, request.isRequired());
        if (request.isRequired() && card == null && !trashOptions.isEmpty()) {
            logger.error("Player {} failed to trash a card when it was both required and possible", player.getName());
            throw new IllegalMoveException("Illegal move detected, exiting game");
        } else if (card == null) {
            logger.info("Player {} chose not to trash a card", player.getName());
        } else if (!deck.trashCard(card, request.getDeckPosition())) {
            logger.error("Player {} tried to remove {} which was not an option", player.getName(), card.getName());
            throw new IllegalMoveException("Illegal move detected, exiting game");
        }
        return card;
    }

    /**
     * Handles when the player is asked to move a card from one place to another
     *
     * @param request The request object
     */
    private void handleMoveCard(MoveCardRequest request) {
        logger.info("Player {} received a request to move the {} card from {} to {}",
                player.getName(),
                request.getCard(),
                request.getFrom(),
                request.getTo());

        if (!deck.moveCard(request.getCard(), request.getFrom(), request.getTo())) {
            logger.error("Card {} wasn't in the {} pile for player {}", request.getCard().getName(), request.getFrom(),
                    player.getName());
            throw new IllegalMoveException("Illegal move detected. Exiting game");
        }
    }

    /**
     * Handles the flow of top decking a card
     *
     * @param request The request to top deck a card
     * @return The card that is top decked
     */
    private Card handleTopDeckRequest(TopDeckRequest request) {
        logger.info("Player {} received a request to put a card from {} onto the top of their deck", player.getName(),
                request.getPosition());
        List<Card> options = deck.getCards(request.getPosition(), request.getCardSpecification());
        Card chosenCard = actionController.chooseTopDeckHook(options, request.isRequired());
        if (chosenCard == null) {
            if (request.isRequired() && !options.isEmpty()) {
                logger.error("Player {} attempted to refuse a top-deck when it was required", player.getName());
                throw new IllegalMoveException("Illegal move detected. Exiting game");
            }
            logger.info("Player {} chose to not top-deck a card", player.getName());
            return null;
        }

        if (!deck.moveCard(chosenCard, request.getPosition(), DeckPosition.DRAW)) {
            logger.error("Player {} attempted to top deck a card from {} when that card wasn't in the position",
                    player.getName(), request.getPosition());
            throw new IllegalMoveException("Illegal move detected. Exiting game");
        }
        return chosenCard;
    }

    /**
     * Handles playing the money in the player's hand
     */
    private void updateMoney() {
        logger.info("Player {} is playing their treasure cards", player.getName());
        List<Card> inHandTreasures = deck.getCards(DeckPosition.HAND,
                new CardSpecification().withType(CardType.TREASURE));
        for (Card card : inHandTreasures) {
            if (!deck.moveCard(card, DeckPosition.HAND, DeckPosition.PLAYED)) {
                logger.error("Attempted to play money card that wasn't in hand");
                throw new RuntimeException("This shouldn't happen");
            }
            card.playCard();
        }

    }

}

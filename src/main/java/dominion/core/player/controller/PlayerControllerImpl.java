package dominion.core.player.controller;

import api.agent.ActionController;
import api.data.CardData;
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
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents the object that will control the player etc. AI, CLI player etc
 */
public class PlayerControllerImpl implements PlayerController {

    protected static final Logger logger = LogManager.getLogger(PlayerControllerImpl.class);

    protected final Player player;
    protected final PlayerDeck deck;
    protected final ActionController actionController;

    /**
     * Registers the player for within the RequestForActionRouter to receive actions
     * for the player
     *
     * @param player The player that this controller is responsible for controlling
     */
    public PlayerControllerImpl(Player player, ActionController actionController) {
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
        }
    }

    /**
     * Returns the action controller responsible for agent decisions
     */
    public ActionController getActionController() {
        return this.actionController;
    }

    /**
     * Handles the process of calling the controller to pick a card to play and
     * proceeds to play the card
     *
     * @return The action response to send back to RFA, null represents no card
     * played otherwise, card played
     */
    protected Card handlePlayActionCard() {
        logger.info("Player {} received request to choose an action card", player.getName());

        Card card = getPickedCard(
                () -> deck.getCards(DeckPosition.HAND, new CardSpecification().withType(CardType.ACTION)),
                actionController::playActionCardHook,
                "play an action");

        if (card == null) {
            logger.info("Player {} chose to not play a card", player.getName());
            return null;
        }

        if (!deck.moveCard(card, DeckPosition.HAND, DeckPosition.PLAYED)) {
            logger.error("Player {} played {} when they did not have it within their hand", player.getName(),
                    card.getName());
            throw new IllegalMoveException("Illegal move detected. Exiting game");
        } else {
            // Plays card after moving, when resolving effects,
            // the played card is treated as though its in the played pile, not the deck
            logger.info("Player {} is playing the card {}", player.getName(), card.getName());
            card.playCard();
            player.updateTurnResources(-1, 0, 0);
        }
        return card;
    }

    /**
     * Processes a buy request, it first fetches the cards available to the player
     * then prompts the implementation to
     * pick one of those cards and then adds it to the players deck
     *
     * @return The card which the player purchased
     */
    protected Card handleBuyCard() {
        logger.info("Player {} received request to buy a card", player.getName());
        updateMoney();

        Card card = getPickedCard(
                () -> KingdomManager.getInstance().getAvailableCards(new CardSpecification().withMaxCost(player.getMoney())),
                actionController::buyCardHook,
                "buy a card");

        if (card == null) {
            logger.info("Player {} has chosen not to buy a card", player.getName());
            return null;
        }
        logger.info("Player {} has chosen to buy a {}", player.getName(), card.getName());
        KingdomManager.getInstance().removeCard(card);
        deck.addCard(card, DeckPosition.DISCARD);
        player.updateTurnResources(0, -1, -card.getCost());
        return card;
    }

    /**
     * End of turn function for the players hand being reset for their next turn
     */
    protected void handleDeckCleanup() {
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
    protected Card handleDiscardFromHand(DiscardFromHandRequest request) {
        logger.info("Player {} received a request to discard a card", player.getName());

        List<Card> options = player.getDeck().getHand();

        Card card = getPickedCard(
                () -> options,
                (List<CardData> cards) -> actionController.discardFromHandHook(cards, request.isRequired()),
                "discard a card"
        );

        if (options.isEmpty()) {
            logger.info("Player {} could not discard a card", player.getName());
            return null;
        }

        if (card == null && request.isRequired()) {
            logger.error("Player {} failed to discard a card when it was both required and possible", player.getName());
            throw new IllegalMoveException("Illegal move detected, exiting game");
        } else if (card == null) {
            logger.info("Player {} chose not to discard a card", player.getName());
        } else if (!deck.moveCard(card, DeckPosition.HAND, DeckPosition.DISCARD)) {
            logger.error("Attempted to discard {} from players hand but it wasn't in their hand", player.getName());
            throw new IllegalMoveException("Illegal move detected, exiting game");
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
    protected List<Card> handleDrawCard(int drawCount) {
        logger.info("Player {} is drawing {} cards", player.getName(), drawCount);
        return deck.draw(drawCount);
    }

    /**
     * Processes a gain card request
     *
     * @param request The request to execute
     */
    protected Card handleGainCardRequest(GainCardRequest request) {
        logger.info("Player {} received a request to gain a card", player.getName());

        List<Card> options = KingdomManager.getInstance().getAvailableCards(request.getCardSpecification());
        Card card = getPickedCard(
                () -> options,
                actionController::gainCardHook,
                "gain a card"
        );

        if (request.isRequired() && card == null && !(options.isEmpty())) {
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
    protected Card handleTrashCard(TrashCardRequest request) {
        logger.info("Player {} received a request to trash a card", player.getName());
        List<Card> cardsInPosition = deck.getCards(request.getDeckPosition());
        List<Card> trashOptions = request.getCardSpecification().filterCards(cardsInPosition);

        Card card = getPickedCard(
                () -> trashOptions,
                (List<CardData> cards) -> actionController.trashCardHook(cards, request.isRequired()),
                "trash a card"
        );

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
    protected void handleMoveCard(MoveCardRequest request) {
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
     * Handles the proxying of converting the options to the card data and back
     * Alongside this, it will handle returning early given there is no options to provide to the player
     *
     * @param getOptions      Supplier to fetch the list of options the agent can choose
     * @param getPickedOption Method reference of the function to call on the agent
     * @param action          String representation of the action that is being completed
     * @return The card that is chosen by the agent
     */
    protected Card getPickedCard(Supplier<List<Card>> getOptions, Function<List<CardData>, CardData> getPickedOption, String action) {
        List<Card> options = getOptions.get();
        if (options.isEmpty()) {
            logger.info("Player {} did not have an option when asked to {}", player.getName(), action);
            return null;
        }
        List<CardData> proxiedOptions = hookCardToDataProxy(options);
        Optional<CardData> pickedOption = Optional.ofNullable(getPickedOption.apply(proxiedOptions));
        return pickedOption.map(option -> hookCardToDataReverseProxy(option, options)).orElse(null);
    }

    /**
     * Handles playing the money in the player's hand
     */
    protected void updateMoney() {
        logger.info("Player {} is playing their treasure cards", player.getName());
        List<Card> inHandTreasures = deck.getCards(DeckPosition.HAND,
                new CardSpecification().withType(CardType.TREASURE));
        for (Card card : inHandTreasures) {
            deck.moveCard(card, DeckPosition.HAND, DeckPosition.PLAYED);
            card.playCard();
        }

    }

    /**
     * Converts the list of options that are provided to the readonly API object {@link CardData}
     *
     * @param cards The list of cards to convert
     * @return The API representation of the list of cards
     */
    private List<CardData> hookCardToDataProxy(List<Card> cards) {
        return cards.stream().map(Card::toCardData).toList();
    }

    /**
     * Takes the response from the agent and feeds it back through the card data proxy to find the original card
     *
     * @param chosenCard the card the agent chose
     * @param cards      the list of cards the chosen card data should have derived from
     * @return The card the player chose
     * @throws IllegalMoveException Thrown if the card data is fabricated by the player and doesnt derive from the original list
     */
    private Card hookCardToDataReverseProxy(CardData chosenCard, List<Card> cards) throws IllegalMoveException {
        return cards.stream().filter(card -> card.toCardData().equals(chosenCard)).findAny().orElseThrow(() -> {
            throw new IllegalMoveException("Illegal move detected. Exiting game");
        });
    }
}

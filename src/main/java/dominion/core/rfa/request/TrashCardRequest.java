package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;

/**
 * Request for the player to trash a card that matches the card specification
 */
public class TrashCardRequest extends ControllerActionRequest<Card> {

    private final CardSpecification cardSpecification;

    private final DeckPosition deckPosition;

    /**
     * Overridden constructor for a request
     *
     * @param player   The player the request will be sent to
     * @param required Whether the trashing is required
     */
    public TrashCardRequest(Player player, boolean required, CardSpecification cardSpecification, DeckPosition deckPosition) {
        super(player, required);
        this.cardSpecification = cardSpecification;
        this.deckPosition = deckPosition;
    }

    public CardSpecification getCardSpecification() {
        return cardSpecification;
    }

    public DeckPosition getDeckPosition() {
        return deckPosition;
    }
}

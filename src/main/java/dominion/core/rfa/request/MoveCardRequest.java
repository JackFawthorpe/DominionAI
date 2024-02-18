package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;
import org.jetbrains.annotations.NotNull;

/**
 * Request for getting the player to move a card from one place to another
 * Note: This is not to be used for drawing cards from the hand in this case you should be using {@link DrawCardRequest}
 */
public class MoveCardRequest extends ControllerActionRequest<Void> {

    private Card card;
    private DeckPosition from;
    /**
     * Overridden constructor for a request
     *
     * @param player   The player the request will be sent to
     * @param card     The card to move (This card must exist within the from pile at the time of requesting)
     * @param from     Where the card is to be moved from
     * @param to       Where the card will be moved to
     */
    public MoveCardRequest(@NotNull Player player, @NotNull Card card,@NotNull DeckPosition from,@NotNull DeckPosition to) {
        super(player, true);
        this.card = card;
        this.from = from;
        this.to = to;
    }

    private DeckPosition to;

    public Card getCard() {
        return card;
    }

    public DeckPosition getFrom() {
        return from;
    }

    public DeckPosition getTo() {
        return to;
    }
}

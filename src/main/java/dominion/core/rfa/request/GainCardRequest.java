package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;
import org.jetbrains.annotations.NotNull;

/**
 * Action to represent asking the player to gain a card
 */
public class GainCardRequest extends ControllerActionRequest<Card> {

    private final CardSpecification cardSpecification;
    private final DeckPosition gainPosition;

    public GainCardRequest(Player player, CardSpecification cardSpecification, DeckPosition gainPosition) {
        super(player, true);
        this.cardSpecification = cardSpecification;
        this.gainPosition = gainPosition;
    }

    public CardSpecification getCardSpecification() {
        return cardSpecification;
    }

    public DeckPosition getGainPosition() {
        return gainPosition;
    }
}

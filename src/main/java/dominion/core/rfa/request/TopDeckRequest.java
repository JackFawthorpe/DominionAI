package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Request for the player to top-deck a card from a given position
 */
public class TopDeckRequest extends ControllerActionRequest<Card> {

    private DeckPosition position;
    private CardSpecification cardSpecification;

    public TopDeckRequest(Player player, boolean required, DeckPosition position, CardSpecification cardSpecification) {
        super(player, required);
        this.position = position;
        this.cardSpecification = cardSpecification;
    }

    public DeckPosition getPosition() {
        return position;
    }

    public CardSpecification getCardSpecification() {
        return cardSpecification;
    }
}

package dominion.core.geb.event;

import dominion.card.Card;
import lombok.Getter;

/**
 * Event triggered when a supply pile from the kingdom is depleted (No cards left)
 */
@Getter
public class SupplyPileDepletedEvent implements GameEvent {

    /**
     * The card within the {@link dominion.core.state.KingdomManager} that was depleted
     */
    private final Card card;

    public SupplyPileDepletedEvent(Card card) {
        this.card = card;
    }
}

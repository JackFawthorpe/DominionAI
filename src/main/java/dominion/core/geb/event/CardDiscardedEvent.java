package dominion.core.geb.event;

import dominion.card.Card;
import lombok.Getter;

/**
 * Event to represent a card being discarded
 */
@Getter
public class CardDiscardedEvent implements GameEvent {

    /**
     * The card that is being discarded
     */
    private final Card card;

    public CardDiscardedEvent(Card card) {
        this.card = card;
    }
}

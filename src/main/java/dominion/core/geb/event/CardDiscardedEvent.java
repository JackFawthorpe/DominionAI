package dominion.core.geb.event;

import dominion.card.Card;

/**
 * Event to represent a card being discarded
 */
public class CardDiscardedEvent implements GameEvent {

    private final Card card;

    public CardDiscardedEvent(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }
}

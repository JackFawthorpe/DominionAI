package dominion.core.geb.event;

import dominion.card.Card;

/**
 * Event to represent a card entering the players hand
 */
public class CardDrawnEvent implements GameEvent {

    private final Card card;

    public CardDrawnEvent(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}

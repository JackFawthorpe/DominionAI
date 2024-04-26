package dominion.core.geb.event;

import dominion.card.Card;
import lombok.Getter;

/**
 * Event to represent a card entering the players hand
 */
@Getter
public class CardDrawnEvent implements GameEvent {

    /**
     * The card that was drawn
     */
    private final Card card;

    public CardDrawnEvent(Card card) {
        this.card = card;
    }
}

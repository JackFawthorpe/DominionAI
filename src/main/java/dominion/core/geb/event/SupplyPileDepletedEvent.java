package dominion.core.geb.event;

import dominion.card.Card;

/**
 * Event triggered when a supply pile from the kingdom is finished (No cards left)
 */
public class SupplyPileDepletedEvent implements GameEvent {

    private final Card depletedCard;

    public SupplyPileDepletedEvent(Card depletedCard) {
        this.depletedCard = depletedCard;
    }

    public Card getDepletedCard() {
        return depletedCard;
    }
}

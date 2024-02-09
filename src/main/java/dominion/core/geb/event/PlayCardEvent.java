package dominion.core.geb.event;

import dominion.card.Card;
import dominion.core.player.Entity.Player;

/**
 * Event to represent the playing of a card
 */
public class PlayCardEvent implements GameEvent {

    private final Player player;
    private final Card card;

    public PlayCardEvent(Card card) {
        this.player = card.getPlayer();
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }
}

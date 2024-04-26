package dominion.core.geb.event;

import dominion.card.Card;
import dominion.core.player.Entity.Player;
import lombok.Getter;

/**
 * Event to represent the playing of a card
 */
@Getter
public class PlayCardEvent implements GameEvent {

    /**
     * The player that played the card
     */
    private final Player player;

    /**
     * The card that was played
     */
    private final Card card;

    public PlayCardEvent(Card card) {
        this.player = card.getPlayer();
        this.card = card;
    }
}

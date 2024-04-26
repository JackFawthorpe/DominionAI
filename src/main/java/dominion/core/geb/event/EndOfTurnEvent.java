package dominion.core.geb.event;

import dominion.core.player.Entity.Player;
import lombok.Getter;

/**
 * Event fired at the end of a players turn
 */
@Getter
public class EndOfTurnEvent implements GameEvent {

    /**
     * The player whose turn has ended
     */
    private final Player player;

    public EndOfTurnEvent(Player player) {
        this.player = player;
    }
}

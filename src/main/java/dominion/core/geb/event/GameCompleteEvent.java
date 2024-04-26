package dominion.core.geb.event;

import dominion.core.player.Entity.Player;
import lombok.Getter;

import java.util.List;

/**
 * Event fired when a game is finished
 */
@Getter
public class GameCompleteEvent implements GameEvent {

    /**
     * The list of players that were in the game
     */
    private final List<Player> players;

    public GameCompleteEvent(List<Player> players) {
        this.players = players;
    }
}

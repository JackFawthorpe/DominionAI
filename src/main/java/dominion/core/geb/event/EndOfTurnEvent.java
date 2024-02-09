package dominion.core.geb.event;

import dominion.core.player.Entity.Player;

public class EndOfTurnEvent implements GameEvent {

    private final Player player;

    public EndOfTurnEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}

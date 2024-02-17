package dominion.core.geb.event;

import dominion.core.player.Entity.Player;

import java.util.List;

public class GameCompleteEvent implements GameEvent {

    private List<Player> players;

    public GameCompleteEvent(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}

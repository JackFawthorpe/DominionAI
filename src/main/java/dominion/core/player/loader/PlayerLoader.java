package dominion.core.player.loader;

import dominion.core.player.Entity.Player;

import java.util.List;

/**
 * Player Loader Interface
 */
public interface PlayerLoader {
    /**
     * Retrieves the player objects to run the game on
     *
     * @return The list of players
     */
    List<Player> getPlayers();
}

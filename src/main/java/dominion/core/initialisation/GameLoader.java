package dominion.core.initialisation;

import dominion.core.player.loader.PlayerLoader;
import dominion.core.state.ResetManager;
import dominion.core.state.RoundRobinManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class responsible for taking the configuration and setting the game up
 */
public class GameLoader {

    private static final Logger logger = LogManager.getLogger(GameLoader.class);
    PlayerLoader playerLoader;

    public GameLoader(PlayerLoader playerLoader) {
        this.playerLoader = playerLoader;
    }

    /**
     * Takes the configuration and runs the required setup to start the game
     */
    public void loadGame() {
        RoundRobinManager.getInstance().setPlayers(playerLoader.getPlayers());
    }

    /**
     * Starts the game
     */
    public List<Integer> startGame() {
        List<Integer> results = RoundRobinManager.getInstance().startGame();
        ResetManager.resetGame();
        return results;
    }

}

package dominion.core.initialisation;

import api.ai.DefaultController;
import api.ai.ThoughtfulBuyerController;
import dominion.core.player.Entity.Player;
import dominion.core.player.controller.PlayerController;
import dominion.core.state.ResetManager;
import dominion.core.state.RoundRobinManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Class responsible for taking the configuration and setting the game up
 */
public class GameLoader {

    private static final Logger logger = LogManager.getLogger(GameLoader.class);
    private GameConfiguration configuration;

    /**
     * Takes the configuration and runs the required setup to start the game
     *
     * @param configuration The configuration to load
     */
    public void loadGame(GameConfiguration configuration) {
        this.configuration = configuration;
        loadPlayers();
    }

    /**
     * Generates the players
     */
    private void loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < configuration.getPlayerCount(); i++) {
            Player player = new Player("Player " + (i + 1));
            players.add(player);
            new PlayerController(player, i <= 1 ? new ThoughtfulBuyerController() : new DefaultController());
        }
        RoundRobinManager.getInstance().setPlayers(players);
    }

    /**
     * Starts the game
     */
    public void startGame() {
        try {
            RoundRobinManager.getInstance().startGame();
            ResetManager.resetGame();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("An error occurred whilst playing the game: {}", e.getMessage());
        }
    }

}

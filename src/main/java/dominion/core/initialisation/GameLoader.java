package dominion.core.initialisation;

import api.ai.DefaultController;
import api.ai.LearningObjectiveAnalysis_1;
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
            Player player = null;
            switch (i) {
                case 0:
                case 1:
                    player = new Player("Default Controller " + (i + 1));
                    new PlayerController(player, new DefaultController());
                    break;
                case 2:
                    player = new Player("Thoughtful Buyer Controller " + (i + 1));
                    new PlayerController(player, new ThoughtfulBuyerController());
                    break;
                case 3:
                    player = new Player("Learning Objective Controller " + (i + 1));
                    new PlayerController(player, new LearningObjectiveAnalysis_1());
                    break;
            }
            players.add(player);
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

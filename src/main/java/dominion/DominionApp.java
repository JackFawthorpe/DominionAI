package dominion;


import dominion.core.initialisation.DefaultConfigurationManager;
import dominion.core.initialisation.GameConfiguration;
import dominion.core.initialisation.GameConfigurationManager;
import dominion.core.initialisation.GameLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import statistics.StatisticsApp;

/**
 * Entry point for the game
 */
public class DominionApp {

    private static final Logger logger = LogManager.getLogger(DominionApp.class);

    /**
     * Entry point of the application
     *
     * @param args Not to be used
     */
    public static void main(String[] args) {
        DominionApp app = new DominionApp();
        try {
            app.initialiseGame();
        } catch (IllegalStateException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Creates the initialization manager and starts the initialization process
     */
    public void initialiseGame() {
        GameConfigurationManager manager = new DefaultConfigurationManager(new GameConfiguration());
        manager.initialiseGame();
        if (manager.getConfiguration().isStatisticsEnabled()) {
            StatisticsApp.getInstance().enable();
        }
        GameLoader gameLoader = new GameLoader();
        gameLoader.loadGame(manager.getConfiguration());
        gameLoader.startGame();
    }
}

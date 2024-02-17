package dominion;


import dominion.core.geb.GameEventBus;
import dominion.core.geb.event.SimulationCompleteEvent;
import dominion.core.initialisation.GameConfiguration;
import dominion.core.initialisation.GameConfigurationManager;
import dominion.core.initialisation.GameLoader;
import dominion.core.initialisation.JSONLoadedConfigurationManager;
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
            app.initialiseSimulation();
        } catch (IllegalStateException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Creates the initialization manager and starts the initialization process
     */
    private void initialiseSimulation() {
        GameConfigurationManager manager = new JSONLoadedConfigurationManager(new GameConfiguration());
        manager.initialiseGame();
        if (manager.getConfiguration().isStatisticsEnabled()) {
            StatisticsApp.getInstance().enable();
        }
        runGames(manager);
    }

    private void runGames(GameConfigurationManager manager) {
        for (int i = 0; i < manager.getConfiguration().getGames(); i++) {
            GameLoader gameLoader = new GameLoader();
            gameLoader.loadGame(manager.getConfiguration());
            gameLoader.startGame();
        }
        GameEventBus.getInstance().notifyListeners(new SimulationCompleteEvent());
    }
}

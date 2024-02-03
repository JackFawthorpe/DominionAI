package dominion.core.initialisation;

/**
 * Class to initialize the configuration of the game. This includes things such as player count, as well as
 * Kingdom cards.
 */
public abstract class GameConfigurationManager {

    GameConfiguration configuration;

    protected GameConfigurationManager(GameConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Loads the configuration required to start the game
     */
    public abstract void initialiseGame();

    public GameConfiguration getConfiguration() {
        return configuration;
    }
}

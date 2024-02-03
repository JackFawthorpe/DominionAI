package dominion.core.initialisation;

public abstract class GameConfigurationManager {

    GameConfiguration configuration;

    protected GameConfigurationManager(GameConfiguration configuration) {
        this.configuration = configuration;
    }

    public abstract void initialiseGame();

    public GameConfiguration getConfiguration() {
        return configuration;
    }
}

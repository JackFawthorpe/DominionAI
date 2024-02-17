package dominion.core.initialisation;

import java.util.List;

/**
 * Configuration manager to load a default configuration of the game (This is the simplest way to start a game
 */
public class DefaultConfigurationManager extends GameConfigurationManager {

    public DefaultConfigurationManager(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void initialiseGame() {
        configuration.setPlayerCount(4);
        configuration.setKingdomCards(List.of("First Card", "Second Card", "Third card"));
        configuration.setStatisticsEnabled(true);
    }
}

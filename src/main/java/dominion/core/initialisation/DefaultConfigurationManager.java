package dominion.core.initialisation;

import java.util.List;

public class DefaultConfigurationManager extends GameConfigurationManager {

    public DefaultConfigurationManager(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void initialiseGame() {
        configuration.setPlayerCount(4);
        configuration.setKingdomCards(List.of("First Card", "Second Card", "Third card"));
    }
}

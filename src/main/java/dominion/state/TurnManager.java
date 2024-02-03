package dominion.state;

import dominion.core.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class responsible for handling the transition of state between the different parts of a players turn
 * as well as the turns themselves
 */
public class TurnManager {

    private static final Logger logger = LogManager.getLogger(TurnManager.class);

    private static TurnManager instance;

    private List<Player> players;
    private int currentPlayerIndex;

    /**
     * Singleton implementation of TurnManager
     *
     * @return Singleton of turn manager
     */
    public static TurnManager getInstance() {
        if (instance == null) {
            logger.info("Instantiating the Turn Manager");
            instance = new TurnManager();
        }
        return instance;
    }

    public void startGame() {
        logger.info("Starting game");
    }
}

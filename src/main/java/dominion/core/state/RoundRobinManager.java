package dominion.core.state;

import dominion.core.geb.GameEventBus;
import dominion.core.geb.event.SimulationCompleteEvent;
import dominion.core.player.Entity.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class responsible for handling the transition of state between the different parts of a players turn
 * as well as the turns themselves
 */
public class RoundRobinManager {

    private static final Logger logger = LogManager.getLogger(RoundRobinManager.class);

    private static RoundRobinManager instance;

    private List<Player> players;

    private int turnCount;

    /**
     * Singleton Implementation of RoundRobinManager
     *
     * @return The singleton
     */
    public static RoundRobinManager getInstance() {
        if (instance == null) {
            logger.info("Instantiating the Turn Manager");
            instance = new RoundRobinManager();
        }
        return instance;
    }

    /**
     * Entry point for the game starting
     */
    public void startGame() {
        logger.info("Starting game");
        int currentPlayerIndex = 0;
        while (!EndGameObserver.getInstance().isGameFinished()) {
            if (currentPlayerIndex == 0) {
                turnCount++;
                logger.info("Starting round {}", turnCount);
            }
            TurnManager.getInstance().playTurn(players.get(currentPlayerIndex));
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        logger.warn("Game has finished. Results are as follows");
        for (Player player : players) {
            logger.warn("Player {} scored {}", player.getName(), player.getPoints());
        }
        GameEventBus.getInstance().notifyListeners(new SimulationCompleteEvent());
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        logger.info("Setting players");
        this.players = players;
    }
}

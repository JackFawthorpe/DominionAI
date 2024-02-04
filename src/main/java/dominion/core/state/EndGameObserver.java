package dominion.core.state;

/**
 * Class responsible for the monitoring of the end of the game
 */
public class EndGameObserver {

    private static EndGameObserver instance;
    private final boolean gameFinished;

    private EndGameObserver() {
        this.gameFinished = false;
    }

    /**
     * Singleton implementation of EndGameObserver
     *
     * @return The singleton of EndGameObserver
     */
    public static EndGameObserver getInstance() {
        if (instance == null) {
            instance = new EndGameObserver();
        }
        return instance;
    }

    public boolean isGameFinished() {
        return gameFinished || RoundRobinManager.getInstance().getTurnCount() > 50;
    }
}

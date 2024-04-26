package dominion.core.state;

import dominion.core.geb.GameEventBus;
import dominion.core.rfa.RequestForActionRouter;

/**
 * Manager responsible for cleaning up the singleton instances between games
 */
public class ResetManager {

    /**
     * Fires the gameReset function of each of the singletons
     */
    public static void resetGame() {
        GameEventBus.gameReset();
        RequestForActionRouter.gameReset();
        EndGameObserver.gameReset();
        KingdomManager.gameReset();
        RoundRobinManager.gameReset();
    }
}

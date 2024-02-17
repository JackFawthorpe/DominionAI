package dominion.core.state;

import dominion.core.geb.GameEventBus;
import dominion.core.rfa.RequestForActionRouter;

public class ResetManager {

    public static void resetGame() {
        GameEventBus.gameReset();
        RequestForActionRouter.gameReset();
        EndGameObserver.gameReset();
        KingdomManager.gameReset();
        RoundRobinManager.gameReset();
    }
}

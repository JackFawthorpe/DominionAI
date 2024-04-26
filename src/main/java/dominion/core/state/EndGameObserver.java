package dominion.core.state;

import dominion.card.supply.Province;
import dominion.core.geb.GameEventBus;
import dominion.core.geb.GameEventListener;
import dominion.core.geb.ListenScope;
import dominion.core.geb.event.SupplyPileDepletedEvent;

/**
 * Class responsible for the monitoring of the end of the game
 */
public class EndGameObserver implements GameEventListener<SupplyPileDepletedEvent> {

    private static EndGameObserver instance;
    private boolean gameFinished;

    private int depletionCounter;

    private EndGameObserver() {
        this.gameFinished = false;
        this.depletionCounter = 0;
        GameEventBus.getInstance().addListener(SupplyPileDepletedEvent.class, this);
    }

    public static void gameReset() {
        getInstance().depletionCounter = 0;
        getInstance().gameFinished = false;
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
        return gameFinished;
    }

    /**
     * Updates the gameFinished boolean
     * The game is over if 3 supply piles deplete or all provinces are bought
     *
     * @param event The Supply Depletion event
     */
    @Override
    public void handleEvent(SupplyPileDepletedEvent event) {
        this.depletionCounter++;
        gameFinished = depletionCounter >= 3 || event.getCard() instanceof Province;
    }

    @Override
    public String getIdentifier() {
        return "Game End Observer";
    }

    @Override
    public ListenScope getScope() {
        return ListenScope.SIMULATION;
    }
}

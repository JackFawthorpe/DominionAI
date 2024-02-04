package dominion.core.state;

import dominion.card.Card;
import dominion.core.player.Player;
import dominion.core.rfa.request.PlayActionRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class responsible for handling the transition of state between the different parts of a players turn
 * as well as the turns themselves
 */
public class TurnManager {

    private static final Logger logger = LogManager.getLogger(TurnManager.class);

    private static TurnManager instance;

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

    /**
     * Entry point for the players turn routine
     *
     * @param player The player to take a turn
     */
    public void playTurn(Player player) {
        handleResetTurnInventory(player);
        handleActionPhase(player);
        handleBuyPhase(player);
        handleCollectPhase(player);
    }

    /**
     * Handles setting the inventory up for a turn.
     * This includes setting actions buys etc.
     *
     * @param player The player to action
     */
    private void handleResetTurnInventory(Player player) {
        logger.info("Resetting player {}'s turn inventory", player.getName());
    }

    /**
     * Handles the Action phase of the turn.
     * This consists of the player either playing an action card or passing.
     * A player can only play an action card if they have at least one action left available
     *
     * @param player The player to action
     */
    private void handleActionPhase(Player player) {
        logger.info("Starting action phase for player {}", player.getName());
        boolean canAction = player.getActions() > 0;
        while (canAction) {
            PlayActionRequest request = new PlayActionRequest(player);
            Card card = request.execute().getResponse();
            canAction = player.getActions() > 0 && card != null;
        }
    }


    private void handleBuyPhase(Player player) {
        logger.info("Starting buy phase for player {}", player.getName());

    }


    private void handleCollectPhase(Player player) {
        logger.info("Starting collect phase for player {}", player.getName());

    }
}

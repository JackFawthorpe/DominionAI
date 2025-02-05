package dominion.core.state;

import dominion.card.Card;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.BuyCardRequest;
import dominion.core.rfa.request.CleanupRequest;
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
        logger.info("Starting {}'s turn", player.getName());
        handleResetTurnInventory(player);
        handleActionPhase(player);
        handleBuyPhase(player);
        handleCleanupPhase(player);
    }

    /**
     * Handles setting the inventory up for a turn.
     * This includes setting actions buys etc.
     *
     * @param player The player to action
     */
    private void handleResetTurnInventory(Player player) {
        logger.info("Resetting player {}'s turn inventory", player.getName());
        player.resetTurnResources();
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

    /**
     * Handles the buy phase of the turn.
     * The player is asked if they want to purchase a card until they run out of buys {@link Player#getBuys()}
     * or they refuse to buy, {@link BuyCardRequest#getResponse()} will be null
     *
     * @param player The player to send buy requests to
     */
    private void handleBuyPhase(Player player) {
        logger.info("Starting buy phase for player {}", player.getName());
        boolean canBuy = player.getBuys() > 0;
        while (canBuy) {
            BuyCardRequest request = new BuyCardRequest(player);
            Card card = request.execute().getResponse();
            canBuy = player.getBuys() > 0 && card != null;
        }
    }

    /**
     * Handles the clean-up phase of the player's turn
     * This includes discarding the hand and played cards as well as drawing 5 new cards for their
     * next turn.
     *
     * @param player The player to perform the action against
     */
    private void handleCleanupPhase(Player player) {
        logger.info("Starting clean up phase for player {}", player.getName());
        CleanupRequest request = new CleanupRequest(player);
        request.execute();
    }
}

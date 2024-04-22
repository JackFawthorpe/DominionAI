package dominion.core.rfa;

import api.agent.ActionController;
import dominion.core.geb.GameEventBus;
import dominion.core.geb.event.AttackReactionEvent;
import dominion.core.player.Entity.Player;
import dominion.core.player.controller.PlayerController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for passing actions from systems within the game to the
 * PlayerController responsible for executing the action
 */
public class RequestForActionRouter {

    private static final Logger logger = LogManager.getLogger(RequestForActionRouter.class);

    private static RequestForActionRouter instance;

    private final HashMap<Player, PlayerController> routes;

    private RequestForActionRouter() {
        this.routes = new HashMap<>();
    }

    public static void gameReset() {
        instance = new RequestForActionRouter();
    }

    /**
     * Singleton implementation of the RequestForActionRouter class
     *
     * @return The singleton of RequestForActionRouter
     */
    public static RequestForActionRouter getInstance() {
        if (instance == null) {
            logger.info("Instantiating RequestForActionRouter");
            instance = new RequestForActionRouter();
        }
        return instance;
    }

    /**
     * Registers a new handler to the routing. This must occur before the route
     *
     * @param playerController The implementation responsible for handling a players
     *                         actions
     * @param player           The player the handler will be responsible for
     */
    public void addHandler(PlayerController playerController, Player player) {
        logger.info("Adding handler for player {}", player.getName());
        routes.put(player, playerController);
    }

    /**
     * Requests that a player performs a given action
     * If the player isn't registered already then this will return null
     *
     * @param playerActionRequest The action to perform
     */
    public void requestAction(ControllerActionRequest<?> playerActionRequest) {
        Player player = playerActionRequest.getPlayer();
        PlayerController playerController = routes.get(player);
        if (playerController == null) {
            logger.error("Attempted to request action from player without handler {}", player.getName());
            return;
        }
        logger.info("Routing action to player {}", player.getName());

        if (playerActionRequest.isAttack()) {
            AttackReactionEvent reactionEvent = new AttackReactionEvent(playerActionRequest.getPlayer());
            GameEventBus.getInstance().notifyListeners(reactionEvent);
            boolean continueAttack = reactionEvent.isToContinue();
            if (!continueAttack) {
                logger.info("Attack action against {} was blocked", player.getName());
                return;
            }
        }

        playerController.handleAction(playerActionRequest);
    }

    /**
     * Finds the player that belongs to the action controller
     *
     * @return The player that belongs to the controller, if none exists then null
     */
    public Player getPlayer(ActionController actionController) {
        for (Map.Entry<Player, PlayerController> entry : routes.entrySet()) {
            Player player = entry.getKey();
            PlayerController controller = entry.getValue();
            if (controller.getActionController().equals(actionController)) {
                return player;
            }
        }
        return null;
    }
}

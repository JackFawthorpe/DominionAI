package dominion.core.rfa;

import dominion.core.player.Player;
import dominion.core.player.PlayerController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * Responsible for passing the actions requested of players to the correct player
 */
public class RequestForActionRouter {

    private static final Logger logger = LogManager.getLogger(RequestForActionRouter.class);

    private static RequestForActionRouter instance;

    private final HashMap<Player, PlayerController> routes;

    /**
     * Singleton implementation of the RequestForActionRouter class
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
     * @param playerController The implementation responsible for handling a players actions
     * @param player The player the handler will be responsible for
     */
    public void addHandler(PlayerController playerController, Player player) {
        logger.info("Adding handler for player {}", player.getName());
        routes.put(player, playerController);
    }

    /**
     * Requests that a player performs a given action
     * If the player isn't registered already then this will return null
     * @param playerActionRequest The action to perform
     * @param player The player that will perform the action
     * @return The response of the action
     */
    public PlayerActionRequestResponse requestAction(PlayerActionRequest playerActionRequest, Player player) {
        PlayerController playerController = routes.get(player);
        if (playerController == null) {
            logger.error("Attempted to request action from player without handler {}", player.getName());
            return null;
        }
        logger.info("Routing action to player {}", player.getName());
        return playerController.handleAction(playerActionRequest);
    }

    private RequestForActionRouter() {
        this.routes = new HashMap<>();
    }
}

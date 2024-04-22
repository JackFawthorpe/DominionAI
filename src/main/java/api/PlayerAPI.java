package api;


import api.agent.ActionController;
import api.data.PlayerData;
import dominion.core.exception.IllegalMoveException;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.state.RoundRobinManager;

import java.util.List;

/**
 * Methods of interacting with the player data of the game
 */
public class PlayerAPI {

    /**
     * Usage: PlayerAPI.getPlayers()
     * Returns a list of the players that are in the game in play order
     */
    public static List<PlayerData> getPlayers() {
        return RoundRobinManager.getInstance()
                .getPlayers().stream()
                .map(Player::toPlayerData)
                .toList();
    }

    /*
     * Usage: PlayerAPI.getMe(this)
     *
     * When called from an agent controller you are passing in the controller to be compared with the other controllers
     *
     */
    public static PlayerData getMe(ActionController actionController) {
        if (actionController == null) {
            throw new IllegalMoveException("You must provide an action controller to get your player data");
        } else {
            return RequestForActionRouter.getInstance().getPlayer(actionController).toPlayerData();
        }
    }

}

package dominion.core.player.loader;

import api.agent.ActionController;
import api.agent.DefaultController;
import dominion.core.initialisation.GameConfiguration;
import dominion.core.player.Entity.Player;
import dominion.core.player.controller.PlayerController;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the Player Loader
 */
public class PlayerLoaderImpl implements PlayerLoader {

    private final List<ActionController> controllersToLoad = List.of(
            new DefaultController(),
            new DefaultController(),
            new DefaultController(),
            new DefaultController()
    );


    GameConfiguration configuration;

    public PlayerLoaderImpl(GameConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Player player = new Player(controllersToLoad.get(i).getClass().getSimpleName() + ' ' + (i + 1));
            new PlayerController(player, controllersToLoad.get(i));
            players.add(player);
        }
        return players;
    }
}

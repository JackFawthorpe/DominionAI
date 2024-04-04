package dominion.core.player.loader;

import api.agent.*;
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
            new DefaultController_1(),
            new ProvincePursuer_1(),
            new ThoughtfulBuyer_1(),
            new UnthoughtfulBuyer_1()
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

package dominion.core.player.loader;

import api.agent.ActionController;
import api.agent.ProvincePursuer_1;
import api.agent.ThoughtfulBuyer_1;
import api.agent.UnthoughtfulBuyer_1;
import competition.SecurePlayerController;
import dominion.core.initialisation.GameConfiguration;
import dominion.core.player.Entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the Player Loader
 */
public class PlayerLoaderImpl implements PlayerLoader {

    private final List<ActionController> controllersToLoad = List.of(
            new ProvincePursuer_1(),
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
            Player player = new Player(controllersToLoad.get(i).getClass().getSimpleName() + ' ' + (i + 1), i + 1);
            new SecurePlayerController(player, controllersToLoad.get(i));
            players.add(player);
        }
        return players;
    }
}

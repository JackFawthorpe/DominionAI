package dominion.core.initialisation;

import dominion.core.player.Player;
import dominion.core.player.controller.DefaultController;
import dominion.state.TurnManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GameLoader {

    private static final Logger logger = LogManager.getLogger(GameLoader.class);
    private GameConfiguration configuration;

    public void loadGame(GameConfiguration configuration) {
        this.configuration = configuration;
        loadPlayers();

        for (String cardName : configuration.getKingdomCards()) {
            logger.info("Loading card {} into the game", cardName);
        }
    }

    public void loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < configuration.getPlayerCount(); i++) {
            Player player = new Player("Player " + (i + 1));
            players.add(player);
            new DefaultController(player);
        }
        TurnManager.getInstance().setPlayers(players);
    }

    public void startGame() {
        TurnManager.getInstance().startGame();
    }

}

package dominion.core.initialisation;

import dominion.card.Card;
import dominion.card.base.Festival;
import dominion.card.supply.Copper;
import dominion.card.supply.Estate;
import dominion.core.player.Player;
import dominion.core.player.PlayerDeck;
import dominion.core.player.controller.DefaultController;
import dominion.core.state.RoundRobinManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Class responsible for taking the configuration and setting the game up
 */
public class GameLoader {

    private static final Logger logger = LogManager.getLogger(GameLoader.class);
    private GameConfiguration configuration;

    /**
     * Takes the configuration and runs the required setup to start the game
     *
     * @param configuration The configuration to load
     */
    public void loadGame(GameConfiguration configuration) {
        this.configuration = configuration;
        loadPlayers();

        for (String cardName : configuration.getKingdomCards()) {
            logger.info("Loading card {} into the game", cardName);
        }
    }

    /**
     * Generates the players
     */
    private void loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < configuration.getPlayerCount(); i++) {
            Player player = new Player("Player " + (i + 1));
            players.add(player);
            initializeDeck(player);
            new DefaultController(player);
        }
        RoundRobinManager.getInstance().setPlayers(players);
    }

    /**
     * Sets up the deck of each player for the start of game
     *
     * @param player The player to initialize
     */
    private void initializeDeck(Player player) {
        PlayerDeck deck = player.getDeck();
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cards.add(new Festival(player));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Copper(player));
        }
        deck.setHand(cards);
        cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cards.add(new Estate(player));
        }
        deck.setDraw(cards);
    }

    /**
     * Starts the game
     */
    public void startGame() {
        try {
            RoundRobinManager.getInstance().startGame();
        } catch (Exception e) {
            logger.error("An error occurred whilst playing the game: {}", e.getMessage());
        }
    }

}

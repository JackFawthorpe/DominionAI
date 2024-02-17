package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.geb.GameEventBus;
import dominion.core.geb.GameEventListener;
import dominion.core.geb.ListenScope;
import dominion.core.geb.event.EndOfTurnEvent;
import dominion.core.geb.event.GameEvent;
import dominion.core.geb.event.PlayCardEvent;
import dominion.core.player.Entity.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Merchant"/>
 */
public class Merchant extends Card implements GameEventListener<GameEvent> {

    private static final Logger logger = LogManager.getLogger(Merchant.class);


    public Merchant(Player player) {
        withCost(3);
        withActions(1);
        withSimpleDraw(1);
        withCardType(CardType.ACTION);
        setPlayer(player);
        withName("Merchant");
    }

    @Override
    protected void playCardHook() {
        GameEventBus.getInstance().addListener(GameEvent.class, this);
    }

    @Override
    public void handleEvent(GameEvent event) {
        if (event instanceof PlayCardEvent playCardEvent) {
            handlePlayCardEvent(playCardEvent);
        } else if (event instanceof EndOfTurnEvent endOfTurnEvent) {
            handleEndOfTurnEvent(endOfTurnEvent);
        }
    }

    /**
     * Card is identified by the owner of the card
     *
     * @return "Player one's Merchant"
     */
    @Override
    public String getIdentifier() {
        return String.format("%s's Merchant", player.getName());
    }

    @Override
    public ListenScope getScope() {
        return ListenScope.GAME;
    }

    /**
     * Checks if the player has played a silver, if so adds one extra money
     *
     * @param event PlayCardEvent to check
     */
    private void handlePlayCardEvent(PlayCardEvent event) {
        if (event.getCard().getName().equals("Silver") && event.getPlayer().equals(player)) {
            logger.info("Player {} has triggered the merchant's +1 money", player.getName());
            player.updateTurnResources(0, 0, 1);
            GameEventBus bus = GameEventBus.getInstance();
            bus.removeListener(GameEvent.class, this);
        }
    }

    /**
     * Checks if the owners turn has happened, if so it removes itself as a listener
     *
     * @param event EndOfTurnEvent to check
     */
    private void handleEndOfTurnEvent(EndOfTurnEvent event) {
        if (event.getPlayer().equals(player)) {
            logger.info("Player {} has lost their merchant passive ability", player.getName());
            GameEventBus bus = GameEventBus.getInstance();
            bus.removeListener(GameEvent.class, this);
        }
    }
}

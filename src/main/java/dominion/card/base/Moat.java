package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.geb.GameEventListener;
import dominion.core.geb.event.AttackReactionEvent;
import dominion.core.geb.event.CardDiscardedEvent;
import dominion.core.geb.event.CardDrawnEvent;
import dominion.core.geb.event.GameEvent;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.DrawCardRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Moat"/>
 */
public class Moat extends Card implements GameEventListener<GameEvent> {

    private static final Logger logger = LogManager.getLogger(Moat.class);
    private boolean isBlocking = false;


    public Moat(Player player) {
        withCost(2);
        withCardType(CardType.ACTION);
        withCardType(CardType.REACTION);
        setPlayer(player);
        withName("Moat");
    }

    @Override
    protected void playCardHook() {
        DrawCardRequest request = new DrawCardRequest(player, 2);
        request.execute();
    }

    @Override
    public void handleEvent(GameEvent event) {
        // If this is drawn
        if (event instanceof CardDrawnEvent cardDrawnEvent && cardDrawnEvent.getCard() == this) {
            isBlocking = true;
        }
        // If this is discarded
        else if (event instanceof CardDiscardedEvent cardDiscardedEvent && cardDiscardedEvent.getCard() == this) {
            isBlocking = false;
        }
        // If an attack has been made
        else if (event instanceof AttackReactionEvent attackReactionEvent && attackReactionEvent.getPlayer().equals(player)) {
            logger.info("Player {}'s moat blocked the incoming attack", player.getName());
            attackReactionEvent.setToContinue(!isBlocking);
        }
    }

    @Override
    public String getIdentifier() {
        return String.format("Player %s's moat card", player.getName());
    }
}

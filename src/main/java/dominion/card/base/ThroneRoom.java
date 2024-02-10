package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.geb.GameEventBus;
import dominion.core.geb.GameEventListener;
import dominion.core.geb.event.PlayCardEvent;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/ThroneRoom"/>
 */
public class ThroneRoom extends Card implements GameEventListener<PlayCardEvent> {


    public ThroneRoom(Player player) {
        withCost(4);
        setPlayer(player);
        withName("Throne Room");
        withCardType(CardType.ACTION);
    }

    @Override
    protected void playCardHook() {
        GameEventBus.getInstance().addListener(PlayCardEvent.class, this);
    }

    @Override
    public void handleEvent(PlayCardEvent event) {
        if (event.getPlayer().equals(player)) {
            event.getCard().playCard();
        }
        GameEventBus.getInstance().removeListener(PlayCardEvent.class, this);
    }

    @Override
    public String getIdentifier() {
        return String.format("Player %s's played Throne Room", player.getName());
    }
}

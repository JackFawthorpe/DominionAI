package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.TopDeckRequest;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Harbinger"/>
 */
public class Harbinger extends Card {

    public Harbinger(Player player) {
        setPlayer(player);
        withCost(3);
        withSimpleDraw(1);
        withActions(1);
        withCardType(CardType.ACTION);
        withName("Harbinger");
    }

    @Override
    protected void playCardHook() {
        TopDeckRequest request = new TopDeckRequest(player, false, DeckPosition.DISCARD, new CardSpecification());
        request.execute();
    }
}

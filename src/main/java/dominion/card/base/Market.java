package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Market"/>
 */
public class Market extends Card {

    public Market(Player player) {
        withActions(1);
        withBuys(1);
        withMoney(1);
        withCost(5);
        withCardType(CardType.ACTION);
        setPlayer(player);
        withName("Market");
        withSimpleDraw(1);
    }
}

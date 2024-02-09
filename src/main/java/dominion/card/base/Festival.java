package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Festival"/>
 */
public class Festival extends Card {

    public Festival(Player player) {
        setPlayer(player);
        withActions(2);
        withBuys(1);
        withMoney(2);
        withCost(5);
        withCardType(CardType.ACTION);
        withName("Festival");
    }
}

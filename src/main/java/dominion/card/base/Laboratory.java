package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Laboratory"/>
 */
public class Laboratory extends Card {
    public Laboratory(Player player) {
        setPlayer(player);
        withActions(1);
        withCost(5);
        withCardType(CardType.ACTION);
        withName("Laboratory");
        withSimpleDraw(2);
    }
}

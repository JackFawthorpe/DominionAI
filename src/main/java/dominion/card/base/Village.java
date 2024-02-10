package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Village"/>
 */
public class Village extends Card {

    public Village(Player player) {
        withActions(2);
        withCost(3);
        setPlayer(player);
        withCardType(CardType.ACTION);
        withName("Village");
        withSimpleDraw(1);
    }
}

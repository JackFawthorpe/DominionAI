package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Militia"/>
 */
public class Militia extends Card {

    public Militia(Player player) {
        withMoney(2);
        withCost(4);
        setOwner(player);
        withName("Militia");
        withCardType(CardType.ACTION);
    }
}

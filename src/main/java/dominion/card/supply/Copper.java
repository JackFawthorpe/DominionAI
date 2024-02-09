package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Copper"/>
 */
public class Copper extends Card {

    public Copper(Player player) {
        setPlayer(player);
        withMoney(1);
        withCost(0);
        withCardType(CardType.TREASURE);
        withName("Copper");
    }

}

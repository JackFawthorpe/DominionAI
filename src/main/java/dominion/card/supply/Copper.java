package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Copper"/>
 */
public class Copper extends Card {

    public Copper(Player player) {
        setOwner(player);
        withMoney(2);
        withCost(3);
        withCardType(CardType.TREASURE);
        withName("Copper");
    }

}

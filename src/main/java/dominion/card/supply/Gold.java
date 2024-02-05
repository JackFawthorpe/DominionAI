package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Gold"/>
 */
public class Gold extends Card {

    public Gold(Player player) {
        setOwner(player);
        withMoney(3);
        withCost(6);
        withCardType(CardType.TREASURE);
        withName("Gold");
    }

}

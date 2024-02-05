package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Province"/>
 */
public class Province extends Card {

    public Province(Player player) {
        setOwner(player);
        withCost(8);
        withVictoryPoints(6);
        withCardType(CardType.VICTORY);
        withName("Province");
    }

}

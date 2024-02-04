package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Estate"/>
 */
public class Estate extends Card {

    public Estate(Player player) {
        setOwner(player);
        withCost(2);
        withVictoryPoints(1);
        withCardType(CardType.VICTORY);
        withName("Estate");
    }

}

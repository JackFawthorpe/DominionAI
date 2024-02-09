package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Estate"/>
 */
public class Estate extends Card {

    public Estate(Player player) {
        setPlayer(player);
        withCost(2);
        withVictoryPoints(1);
        withCardType(CardType.VICTORY);
        withName("Estate");
    }

}

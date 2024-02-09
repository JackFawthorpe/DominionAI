package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Moat"/>
 */
public class Moat extends Card {

    public Moat(Player player) {
        setPlayer(player);
        withName("Moat");
    }
}

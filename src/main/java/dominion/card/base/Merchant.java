package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Merchant"/>
 */
public class Merchant extends Card {

    public Merchant(Player player) {
        setOwner(player);
        withName("Merchant");
    }
}

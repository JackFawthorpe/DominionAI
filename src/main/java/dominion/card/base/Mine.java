package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Mine"/>
 */
public class Mine extends Card {
    public Mine(Player player) {
        setOwner(player);
        withName("Mine");
    }
}

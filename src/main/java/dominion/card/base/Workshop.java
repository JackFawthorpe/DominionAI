package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Workshop"/>
 */
public class Workshop extends Card {

    public Workshop(Player player) {
        setOwner(player);
        withName("Workshop");
    }
}

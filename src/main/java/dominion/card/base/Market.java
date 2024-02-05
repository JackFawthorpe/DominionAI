package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Market"/>
 */
public class Market extends Card {

    public Market(Player player) {
        setOwner(player);
        withName("Market");
    }
}

package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Village"/>
 */
public class Village extends Card {

    public Village(Player player) {
        setOwner(player);
        withName("Village");
    }
}

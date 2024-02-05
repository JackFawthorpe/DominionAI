package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Militia"/>
 */
public class Militia extends Card {

    public Militia(Player player) {
        setOwner(player);
        withName("Militia");
    }
}

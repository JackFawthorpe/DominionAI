package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Cellar"/>
 */
public class Cellar extends Card {

    public Cellar(Player player) {
        setOwner(player);
        withName("Cellar");
    }

}

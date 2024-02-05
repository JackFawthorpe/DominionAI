package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Remodel"/>
 */
public class Remodel extends Card {

    public Remodel(Player player) {
        setOwner(player);
        withName("Remodel");
    }
}

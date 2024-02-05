package dominion.card.base;

import dominion.card.Card;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Smithy"/>
 */
public class Smithy extends Card {
    public Smithy(Player player) {
        setOwner(player);
        withName("Smithy");
    }
}

package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Smithy"/>
 */
public class Smithy extends Card {

    private static final Logger logger = LogManager.getLogger(Smithy.class);

    public Smithy(Player player) {
        setPlayer(player);
        withName("Smithy");
        withCost(4);
        withCardType(CardType.ACTION);
        withSimpleDraw(3);
    }
}

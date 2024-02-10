package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Gardens"/>
 */
public class Gardens extends Card {


    public Gardens(Player player) {
        setPlayer(player);
        withName("Gardens");
        withCost(4);
        withCardType(CardType.VICTORY);
    }

    /**
     * The garden is worth 10 points for every 10 cards that the player owns
     *
     * @return Players deck size / 10 rounded down
     */
    @Override
    public int getVictoryPoints() {
        return player.getDeck().getAllCards().size() / 10;
    }
}

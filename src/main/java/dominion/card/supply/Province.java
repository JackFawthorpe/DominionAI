package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Province"/>
 */
public class Province extends Card {

    public Province() {
        withCost(8);
        withVictoryPoints(6);
        withCardType(CardType.VICTORY);
        withName("Province");
    }

}

package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Copper"/>
 */
public class Copper extends Card {

    public Copper() {
        withMoney(2);
        withCost(3);
        withCardType(CardType.TREASURE);
        withName("Copper");
    }

}

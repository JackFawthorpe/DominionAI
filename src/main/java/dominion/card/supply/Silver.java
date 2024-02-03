package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Silver"/>
 */
public class Silver extends Card {

    public Silver() {
        withMoney(2);
        withCost(3);
        withCardType(CardType.TREASURE);
        withName("Silver");
    }

}

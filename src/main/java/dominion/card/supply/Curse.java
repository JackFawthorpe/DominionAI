package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Curse"/>
 */
public class Curse extends Card {

    public Curse() {
        withCost(0);
        withVictoryPoints(-1);
        withCardType(CardType.CURSE);
        withName("Curse");
    }

}

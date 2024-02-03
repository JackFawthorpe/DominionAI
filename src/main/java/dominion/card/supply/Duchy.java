package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;


/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Duchy"/>
 */
public class Duchy extends Card {

    public Duchy() {
        withCost(5);
        withVictoryPoints(3);
        withCardType(CardType.VICTORY);
        withName("Duchy");
    }

}

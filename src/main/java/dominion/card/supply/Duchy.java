package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;

public class Duchy extends Card {

    public Duchy() {
        withCost(5);
        withVictoryPoints(3);
        withCardType(CardType.VICTORY);
        withName("Duchy");
    }

}

package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;

public class Copper extends Card {

    public Copper() {
        withMoney(2);
        withCost(3);
        withCardType(CardType.TREASURE);
        withName("Copper");
    }

}

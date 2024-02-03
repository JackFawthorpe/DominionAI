package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;

public class Gold extends Card {

    public Gold() {
        withMoney(3);
        withCost(6);
        withCardType(CardType.TREASURE);
        withName("Gold");
    }

}

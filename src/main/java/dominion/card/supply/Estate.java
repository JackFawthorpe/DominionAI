package dominion.card.supply;

import dominion.card.Card;
import dominion.card.CardType;

public class Estate extends Card {

    public Estate() {
        withCost(2);
        withVictoryPoints(1);
        withCardType(CardType.VICTORY);
        withName("Estate");
    }

}

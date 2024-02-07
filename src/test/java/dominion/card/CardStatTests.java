package dominion.card;

import dominion.card.base.Cellar;
import dominion.card.supply.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testing.utilities.TestSuite;

class CardStatTests extends TestSuite {

    @Test
    void copperTest() {
        Card card = new Copper(mockPlayer);

        Assertions.assertEquals("Copper", card.getName());
        Assertions.assertEquals(0, card.getCost());
        Assertions.assertEquals(1, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertEquals(CardType.TREASURE, card.getCardType());
        Assertions.assertEquals(mockPlayer, card.getOwner());
    }

    @Test
    void silverTest() {
        Card card = new Silver(mockPlayer);

        Assertions.assertEquals("Silver", card.getName());
        Assertions.assertEquals(3, card.getCost());
        Assertions.assertEquals(2, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertEquals(CardType.TREASURE, card.getCardType());
        Assertions.assertEquals(mockPlayer, card.getOwner());
    }

    @Test
    void goldTest() {
        Card card = new Gold(mockPlayer);

        Assertions.assertEquals("Gold", card.getName());
        Assertions.assertEquals(6, card.getCost());
        Assertions.assertEquals(3, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertEquals(CardType.TREASURE, card.getCardType());
        Assertions.assertEquals(mockPlayer, card.getOwner());
    }

    @Test
    void provinceTest() {
        Card card = new Province(mockPlayer);

        Assertions.assertEquals("Province", card.getName());
        Assertions.assertEquals(8, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(6, card.getVictoryPoints());
        Assertions.assertEquals(CardType.VICTORY, card.getCardType());
        Assertions.assertEquals(mockPlayer, card.getOwner());
    }

    @Test
    void duchyTest() {
        Card card = new Duchy(mockPlayer);

        Assertions.assertEquals("Duchy", card.getName());
        Assertions.assertEquals(5, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(3, card.getVictoryPoints());
        Assertions.assertEquals(CardType.VICTORY, card.getCardType());
        Assertions.assertEquals(mockPlayer, card.getOwner());
    }

    @Test
    void estateTest() {
        Card card = new Estate(mockPlayer);

        Assertions.assertEquals("Estate", card.getName());
        Assertions.assertEquals(2, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(1, card.getVictoryPoints());
        Assertions.assertEquals(CardType.VICTORY, card.getCardType());
        Assertions.assertEquals(mockPlayer, card.getOwner());
    }

    @Test
    void curseTest() {

        Card card = new Curse(mockPlayer);

        Assertions.assertEquals("Curse", card.getName());
        Assertions.assertEquals(0, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(-1, card.getVictoryPoints());
        Assertions.assertEquals(CardType.CURSE, card.getCardType());
        Assertions.assertEquals(mockPlayer, card.getOwner());
    }

    @Test
    void cellarTest() {

        Card card = new Cellar(mockPlayer);

        Assertions.assertEquals("Cellar", card.getName());
        Assertions.assertEquals(2, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(1, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertEquals(CardType.ACTION, card.getCardType());
        Assertions.assertEquals(mockPlayer, card.getOwner());
    }
}

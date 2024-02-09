package dominion.card;

import dominion.card.base.*;
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
        Assertions.assertTrue(card.isType(CardType.TREASURE));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
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
        Assertions.assertTrue(card.isType(CardType.TREASURE));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
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
        Assertions.assertTrue(card.isType(CardType.TREASURE));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
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
        Assertions.assertTrue(card.isType(CardType.VICTORY));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
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
        Assertions.assertTrue(card.isType(CardType.VICTORY));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
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
        Assertions.assertTrue(card.isType(CardType.VICTORY));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
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
        Assertions.assertTrue(card.isType(CardType.CURSE));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
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
        Assertions.assertTrue(card.isType(CardType.ACTION));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
    }

    @Test
    void mineTest() {

        Card card = new Mine(mockPlayer);

        Assertions.assertEquals("Mine", card.getName());
        Assertions.assertEquals(5, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertTrue(card.isType(CardType.ACTION));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
    }

    @Test
    void marketTest() {

        Card card = new Market(mockPlayer);

        Assertions.assertEquals("Market", card.getName());
        Assertions.assertEquals(5, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(1, card.getBuys());
        Assertions.assertEquals(1, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertTrue(card.isType(CardType.ACTION));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
    }

    @Test
    void remodelTest() {

        Card card = new Remodel(mockPlayer);

        Assertions.assertEquals("Remodel", card.getName());
        Assertions.assertEquals(4, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertTrue(card.isType(CardType.ACTION));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
    }

    @Test
    void villageTest() {

        Card card = new Village(mockPlayer);

        Assertions.assertEquals("Village", card.getName());
        Assertions.assertEquals(3, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(2, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertTrue(card.isType(CardType.ACTION));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
    }

    @Test
    void smithyTest() {

        Card card = new Smithy(mockPlayer);

        Assertions.assertEquals("Smithy", card.getName());
        Assertions.assertEquals(4, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertTrue(card.isType(CardType.ACTION));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
    }

    @Test
    void workshopTest() {

        Card card = new Workshop(mockPlayer);

        Assertions.assertEquals("Workshop", card.getName());
        Assertions.assertEquals(3, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(0, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertTrue(card.isType(CardType.ACTION));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
    }

    @Test
    void merchantTest() {

        Card card = new Merchant(mockPlayer);

        Assertions.assertEquals("Merchant", card.getName());
        Assertions.assertEquals(3, card.getCost());
        Assertions.assertEquals(0, card.getMoney());
        Assertions.assertEquals(0, card.getBuys());
        Assertions.assertEquals(1, card.getActions());
        Assertions.assertEquals(0, card.getVictoryPoints());
        Assertions.assertTrue(card.isType(CardType.ACTION));
        Assertions.assertEquals(mockPlayer, card.getPlayer());
    }
}

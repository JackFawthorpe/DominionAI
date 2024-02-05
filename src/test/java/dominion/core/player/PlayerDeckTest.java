package dominion.core.player;

import dominion.card.Card;
import dominion.card.supply.Copper;
import dominion.card.supply.Province;
import dominion.core.player.Entity.PlayerDeck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testing.utilities.TestSuite;

import java.util.List;

class PlayerDeckTest extends TestSuite {

    PlayerDeck deck;

    @BeforeEach
    void setup() {
        deck = new PlayerDeck(mockPlayer);
    }

    @Test
    void createDeck_baseCardsAreAdded() {
        int estateCounter = 0;
        int copperCounter = 0;
        List<Card> toCheck = deck.getDraw();
        for (int i = 0; i < 5; i++) {
            if (toCheck.get(i).getName().equals("Copper")) {
                copperCounter++;
            } else if (toCheck.get(i).getName().equals("Estate")) {
                estateCounter++;
            }
        }
        toCheck = deck.getHand();
        for (int i = 0; i < 5; i++) {
            if (toCheck.get(i).getName().equals("Copper")) {
                copperCounter++;
            } else if (toCheck.get(i).getName().equals("Estate")) {
                estateCounter++;
            }
        }

        Assertions.assertEquals(7, copperCounter);
        Assertions.assertEquals(3, estateCounter);
        Assertions.assertEquals(5, deck.getDraw().size());
        Assertions.assertEquals(5, deck.getHand().size());
    }

    @Test
    void deckAccess_CannotModifyDeck() {
        Assertions.assertThrows(
                UnsupportedOperationException.class, () -> {
                    deck.getDraw().add(new Copper(mockPlayer));
                });
        Assertions.assertThrows(
                UnsupportedOperationException.class, () -> {
                    deck.getPlayed().add(new Copper(mockPlayer));
                });
        Assertions.assertThrows(
                UnsupportedOperationException.class, () -> {
                    deck.getHand().add(new Copper(mockPlayer));
                });
        Assertions.assertThrows(
                UnsupportedOperationException.class, () -> {
                    deck.getDiscard().add(new Copper(mockPlayer));
                });
    }

    @Test
    void playCard_MovesCardFromHandToPlayed() {
        deck.playCard(new Copper(mockPlayer)); // Guaranteed to be in hand
        Assertions.assertEquals(4, deck.getHand().size());
        Assertions.assertEquals(1, deck.getPlayed().size());
        Assertions.assertEquals("Copper", deck.getPlayed().get(0).getName());
    }

    @Test
    void playCard_returnsFalseIfFails() {
        Assertions.assertFalse(deck.playCard(new Province(mockPlayer)));
        Assertions.assertEquals(5, deck.getHand().size());
        Assertions.assertEquals(0, deck.getPlayed().size());
    }
}

package api.data;

import java.util.List;

/**
 * An object to contain the data about the deck a player has
 */
public class DeckData {
    private final List<CardData> cardList;

    public DeckData(List<CardData> cardList) {
        this.cardList = cardList;
    }

    /**
     * This represents the list of cards that are within a deck
     */
    public List<CardData> getCardList() {
        return cardList;
    }

}

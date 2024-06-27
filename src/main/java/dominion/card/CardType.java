package dominion.card;

import api.data.CardTypeData;

/**
 * Enum to represent the different types of cards in the game
 */
public enum CardType {
    ACTION("Action"),
    TREASURE("Treasure"),
    VICTORY("Victory"),
    CURSE("Curse"),
    REACTION("Reaction"),
    ATTACK("Attack");

    private final String displayName;

    CardType(String displayName) {
        this.displayName = displayName;
    }

    public CardTypeData toData() {
        return CardTypeData.valueOf(this.toString());
    }

    @Override
    public String toString() {
        return displayName;
    }
}

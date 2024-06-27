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
        switch (this.displayName) {
            case "Action" -> {
                return CardTypeData.ACTION;
            }
            case "Treasure" -> {
                return CardTypeData.TREASURE;
            }
            case "Victory" -> {
                return CardTypeData.VICTORY;
            }
            case "Curse" -> {
                return CardTypeData.CURSE;
            }
            case "Reaction" -> {
                return CardTypeData.REACTION;
            }
            case "Attack" -> {
                return CardTypeData.ATTACK;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

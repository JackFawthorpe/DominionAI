package dominion.card;

/**
 * Enum to represent the different types of cards in the game
 */
public enum CardType {
    ACTION("Action"),
    TREASURE("Treasure"),
    VICTORY("Victory"),
    CURSE("Curse");

    private final String displayName;

    CardType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

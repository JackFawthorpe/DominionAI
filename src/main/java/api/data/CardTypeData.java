package api.data;

/**
 * Enum to represent the different types of cards in the game
 */
public enum CardTypeData {
    ACTION("Action"),
    TREASURE("Treasure"),
    VICTORY("Victory"),
    CURSE("Curse"),
    REACTION("Reaction"),
    ATTACK("Attack");

    private final String displayName;

    CardTypeData(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

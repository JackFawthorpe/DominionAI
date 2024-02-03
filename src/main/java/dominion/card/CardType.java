package dominion.card;

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

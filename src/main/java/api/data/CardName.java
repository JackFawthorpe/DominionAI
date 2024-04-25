package api.data;

/**
 * List of cards that are available, as an example to check if a card is a copper you can use
 * card.getCardName().equals(CardName.COPPER)
 */
public enum CardName {
    COPPER("Copper"),
    CURSE("Curse"),
    ESTATE("Estate"),
    SILVER("Silver"),
    DUCHY("Duchy"),
    GOLD("Gold"),
    PROVINCE("Province"),
    CELLAR("Cellar"),
    MOAT("Moat"),
    MERCHANT("Merchant"),
    VILLAGE("Village"),
    WORKSHOP("Workshop"),
    MILITIA("Militia"),
    REMODEL("Remodel"),
    SMITHY("Smithy"),
    MARKET("Market"),
    MINE("Mine");

    private final String displayName;

    CardName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
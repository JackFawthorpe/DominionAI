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
    CHAPEL("Chapel"),
    MOAT("Moat"),
    HARBINGER("Harbinger"),
    MERCHANT("Merchant"),
    VASSAL("Vassal"),
    VILLAGE("Village"),
    WORKSHOP("Workshop"),
    BUREAUCRAT("Bureaucrat"),
    GARDENS("Gardens"),
    MILITIA("Militia"),
    MONEYLENDER("Moneylender"),
    POACHER("Poacher"),
    REMODEL("Remodel"),
    SMITHY("Smithy"),
    THRONEROOM("Throneroom"),
    BANDIT("Bandit"),
    COUNCILROOM("Councilroom"),
    FESTIVAL("Festival"),
    LABORATORY("Laboratory"),
    LIBRARY("Library"),
    MARKET("Market"),
    MINE("Mine"),
    SENTRY("Sentry"),
    WITCH("Witch"),
    ARTISAN("Artisan");

    private final String displayName;

    CardName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
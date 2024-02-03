package dominion.core.rfa;

public enum PlayerActionRequestCode {
    CHOOSE_ACTION_REQUEST("Choose an action");

    private final String displayName;

    PlayerActionRequestCode(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

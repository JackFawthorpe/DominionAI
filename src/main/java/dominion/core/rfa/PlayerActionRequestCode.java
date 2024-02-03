package dominion.core.rfa;

public enum PlayerActionRequestCode {
    PLAY_ACTION_CARD("Play an action");

    private final String displayName;

    PlayerActionRequestCode(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

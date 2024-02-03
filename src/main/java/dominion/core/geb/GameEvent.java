package dominion.core.geb;

/**
 * Representation of events that can be created by a player doing something etc. Playing a card, ending their turn
 */
public abstract class GameEvent {

    private GameEventCode code;

    private Object[] arguments;

    public GameEventCode getCode() {
        return code;
    }

    public Object[] getArguments() {
        return arguments;
    }
}

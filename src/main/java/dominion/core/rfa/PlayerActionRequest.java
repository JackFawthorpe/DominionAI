package dominion.core.rfa;

/**
 * Representation of an ActionRequest message for a player that specifies a type of action to perform as well as the
 * arguments that are required to perform said action
 */
public abstract class PlayerActionRequest {

    private final PlayerActionRequestCode code;
    private final Object[] arguments;

    protected PlayerActionRequest(PlayerActionRequestCode code, Object[] arguments) {
        this.code = code;
        this.arguments = arguments;
    }

    public PlayerActionRequestCode getCode() {
        return code;
    }

    public Object[] getArguments() {
        return arguments;
    }
}

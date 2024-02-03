package dominion.core.rfa.request;

import dominion.core.player.Player;
import dominion.core.rfa.PlayerActionRequestCode;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.rfa.response.PlayerActionResponse;

/**
 * Representation of an ActionRequest message for a player that specifies a type of action to perform as well as the
 * arguments that are required to perform said action
 */
public abstract class PlayerActionRequest {

    private final PlayerActionRequestCode code;
    private final Object[] arguments;

    private final Player player;

    protected PlayerActionRequest(PlayerActionRequestCode code, Player player, Object[] arguments) {
        this.code = code;
        this.arguments = arguments;
        this.player = player;
    }

    public PlayerActionResponse execute() {
        return RequestForActionRouter.getInstance().requestAction(this);
    }

    public PlayerActionRequestCode getCode() {
        return code;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Player getPlayer() {
        return player;
    }
}
